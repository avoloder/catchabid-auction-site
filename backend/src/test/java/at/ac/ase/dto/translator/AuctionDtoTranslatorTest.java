package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.entities.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AuctionDtoTranslatorTest {

    private AuctionDtoTranslator translator = new AuctionDtoTranslator();

    @Test
    public void testToDto() {
        AuctionPost entity = createAuctionPost();
        AuctionPostDTO dto = translator.toDto(entity);
        assertEqualValues(dto, entity);
    }

    @Test
    public void testToDtoWithNullValues() {
        AuctionPost entity = createAuctionPost();
        entity.setCreator(null);
        entity.setHighestBid(null);

        AuctionPostDTO dto = translator.toDto(entity);

        assertNull(dto.getHighestBid());
        assertNull(dto.getHighestBid());
    }

    @Test
    public void testToDtoList() {
        List<AuctionPost> auctions = Arrays.asList(createAuctionPost(), createAuctionPost());
        List<AuctionPostDTO> AuctionPostDTOs = translator.toDtoList(auctions);
        AuctionPostDTOs.forEach((dto) -> assertEqualValues(dto, auctions.get(0)));
    }

    private void assertEqualValues(AuctionPostDTO dto, AuctionPost entity)
    {
        AuctionHouse auctionHouse = entity.getCreator();
        Bid bid = entity.getHighestBid();

        assertEquals(dto.getCreatorName(), auctionHouse.getName());
        assertEquals(dto.getHighestBid(), bid.getOffer());
        assertEquals(dto.getCategory(), entity.getCategory().name());
        assertEquals(dto.getStatus(), entity.getStatus().name());
        assertEquals(dto.getStartTime(), entity.getStartTime());
        assertEquals(dto.getEndTime(), entity.getEndTime());
    }

    private AuctionPost createAuctionPost()
    {
        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.setName("name");

        Bid bid = new Bid();
        bid.setOffer(2.1);

        AuctionPost post = new AuctionPost();
        post.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0));
        post.setEndTime(LocalDateTime.of(2020, 1, 1, 1, 0));
        post.setCreator(auctionHouse);
        post.setHighestBid(bid);
        post.setCategory(Category.CAR);
        post.setStatus(Status.ACTIVE);

        return post;
    }
}
