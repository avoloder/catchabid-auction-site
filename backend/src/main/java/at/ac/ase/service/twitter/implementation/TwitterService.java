package at.ac.ase.service.twitter.implementation;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.TwitterPostType;
import at.ac.ase.service.twitter.ITwitterService;
import at.ac.ase.util.exceptions.TwitterServiceException;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TwitterService implements ITwitterService {

    private static final Logger logger = LoggerFactory.getLogger(TwitterService.class);

    private String postMediaApiUrl = "";
    private String postTweetApiUrl = "https://api.twitter.com/1.1/statuses/update.json";

    @Override
    public void tweetAuction(AuctionPost auction, TwitterPostType tweetType) {

//        String imageContent = getEncodedImageContent(auction);
//
//        if(imageContent != null) {
//
//            HttpParameters parameters = new HttpParameters();
//            parameters.put("media", imageContent);
//
//            try {
//                postRequest(postMediaApiUrl, parameters);
//            } catch (TwitterServiceException e) {
//                logger.error("Failed to access twitter API while uploading auction image. Error: "  + e.getMessage());
//                return;
//            }
//        }

        HttpParameters parameters = new HttpParameters();
        parameters.put("status", createTweetContent(auction, tweetType));

        try {
            postRequest(postTweetApiUrl, parameters);
        } catch (TwitterServiceException e) {
            logger.error("Failed to access twitter API while posting tweet. Error: "  + e.getMessage());
        }
    }

    private String getEncodedImageContent(AuctionPost auctionPost) {
        if(auctionPost.getImage() != null) {
            return Base64.getEncoder().encodeToString(auctionPost.getImage());
        }
        return null;
    }

    private String createTweetContent(AuctionPost auction, TwitterPostType tweetType) {
        return "Hello World! What's up?";
    }

    private int postRequest(String requestUrl, HttpParameters parameter) throws TwitterServiceException {

        String key = "wYOZXqLFA517eXhu4mmzVqb6j";
        String key_secret = "oAygNHNtjagXxzWrI1502lArm53yXtunbol90Kqk3aHtxO6xNN";
        String token = "1351591898486607872-nuO3TYFk7o7Iu8iag2xQs9MdH98D7X";
        String token_secret = "1HOeuOTivN6HOGx1SpaWNFdsIWpZDMkn9wTGf8ntw6heV";

        DefaultOAuthConsumer consumer = new DefaultOAuthConsumer(key, key_secret);
        consumer.setTokenWithSecret(token, token_secret);
        consumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());

        try {
            URL url = new URL(String.format("%s?%s", requestUrl, createParameterQueryString(parameter)));

            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            request.setConnectTimeout(3000);

            request.setRequestMethod("POST");
            request.setDoOutput(true);

            consumer.sign(request);

            request.connect();

            logHeader(request);

            String response = request.getResponseMessage();

            System.out.println("RESPONSE:");
            System.out.println(response);
            System.out.println("RESP-CODE: " + request.getResponseCode());

            request.disconnect();

            return request.getResponseCode();

        } catch (OAuthExpectationFailedException | OAuthMessageSignerException | IOException e) {
            throw new TwitterServiceException(e.getMessage());
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String createParameterQueryString(HttpParameters parameters) {
        return parameters.entrySet()
                .stream()
                .map(param -> String.format("%s=%s",
                        URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8),
                        URLEncoder.encode(param.getValue().first(), StandardCharsets.UTF_8)))
                .collect(Collectors.joining("&"));
    }

    private static void logHeader(HttpURLConnection httpCon) {
        System.out.println("HEADER: ");
        Map<String, List<String>> hdrs = httpCon.getHeaderFields();
        Set<String> hdrKeys = hdrs.keySet();

        for (String k : hdrKeys)
            System.out.println("Key: " + k + "  Value: " + hdrs.get(k));
        System.out.println();
    }
}
