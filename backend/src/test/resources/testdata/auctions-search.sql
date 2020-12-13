INSERT INTO public.regular_user (id, active, email, first_name, last_name, password_hash, phone_nr) VALUES (1, true, 'test@test.com', 'Robert', 'Richard', '9999', '123');

INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr) VALUES (2, true, 'test@test.com', 'Alice', '9999', '123');
INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr) VALUES (3, true, 'test2@test.com', 'Bob', '3333', '312');

INSERT INTO public.auction_post (id, city, country, house_nr, street, category, description, end_time, min_price, name, start_time, status, creator, bid_id) VALUES ((10000), null, 'Germany', null, null, 'ELECTRONICS', 'Desktop PC - Intel i7, AMD RX 580 8GB', '2020-01-01 10:00:00', 3, 'Item1', '2020-01-01 00:00:00', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, city, country, house_nr, street, category, description, end_time, min_price, name, start_time, status, creator, bid_id) VALUES (10001, null, 'Austria', null, null, 'MUSIC', 'Bob Marley Tickets', '2020-01-01 12:00:00', 6, 'Item2', '2020-01-01 05:00:00', 'ACTIVE', 2, null);
INSERT INTO public.auction_post (id, city, country, house_nr, street, category, description, end_time, min_price, name, start_time, status, creator, bid_id) VALUES (10002, null, 'Austria', null, null, 'OTHER', 'Ticket to Paradise CD', '2020-01-01 20:00:00', 99, 'Item3', '2020-01-01 00:00:00', 'UPCOMING', 3, null);



INSERT INTO public.auction_post (id, city, country, house_nr, street, category, description, end_time, image, min_price, name, start_time, status, creator, bid_id) VALUES (100090, 'Graz', 'Austria', 44, 'Addr', 'FURNITURE', 'cosy couch, very comfortable!', '2020-11-13 12:13:00.000000', E'\\xFFD8FFE000104A46494600010100000100010000FFDB00840009060712101210101012161515151515101515151516161517171515171617151615181D2820181A251B151521312125292B2E2E2E171F3338332D37282D2E2B010A0A0A0E0D0E17100F1B2B1D151E2B2B2B2E2D372D2B2B2D2D2B372B2B2D2D2D372B372D2B2D372B2D2D2B2D2D2D2D2B2B2B2B2B2D2B382D2D2B2D2B2B372B2DFFC000110800C2010303012200021101031101FFC4001B00000203010101000000000000000000000001020304050607FFC400441000020102010707090507040301000000000102031104051221314151911352617181A1B1061422539293C1D1F04262D2D3E11623324382B2F16372A2E23383C215FFC40017010101010100000000000000000000000000010203FFC4001E11010002030003010100000000000000000001110212510321311341FFDA000C03010002110311003F00FAF08620010C0040000210C40210C40210D88042631009886201098D8804218804C436201310C402001000804002632200C43220000007700620103188040000210C402621B100322C6C4C0421B1008431008431008431301086201086440040C4020010008620108620001001DE018804218008431301310D8808B006002626362013103100081880191188004020022488B01098D880421880421B1301086201086260002003D0031880421B1009886201322C6C402621B10032236201310310008040020100310C40210C880080400260C402131880426362001000080000F422010031301008004026260D907201B1117508BA8058C8B20EA09D4026C8907505CA01608AF94172805822B7505CA0163132BE505CA0161123CA11E5009810CF167813111CF0CF01B237232990CF02D020A434C0621880400303BE21000080180999328D7708ACDD0DE8BEE35B39F955E8875B5C57E858FA93F1C3AD5AA3BDEA4DFF00535E0677525CE97B4FE66EC4595CC551A3AB9AA7565BDF16467276D2DF799F15894B69A2F749ADAAFC408293DFE2464C53666AB88B01A2351BBE9DC17666C9F5B3DCD753FEE34C9D8033CAA52D962356763154C57A5157DABC48AE84A096CEE22E2689ADA513D05106116DF1B6D33D6AD62FC17A51BF4BF91017129FD692734515276D611639B232A8CCD4EBDE4A3D7D3A917D440295528AB8D943F864D7536BE2152461AF2B857ADC978F954A70949DDE94FB1B57EE3A10A879AF276AFEE92FBD2FEE67729C8E52E90DF199629192132E8C882FB8882912B943B811CE1107A21008A195622BC60AF276D9D2FA122C39B96E9B714D6CD1C6DF2F02C12C3947CA08C745DC55EDF4CE0E56C7CA7179B269AF4A2EFA9AD4FA4CD9468E727739F86A97834F5AD06E1CE5664CF295D65E9E896A92DCEF66BB99D1789523E7F51F2388A91D4A4F945DBAFBD3E2777098EBAD66AD1D9C4D34F59BA95A108C75D925C11C5589B9A9E22FFE40D752666AB04C83ABBC4EA016E4DA0A2E6D6E4BC4D752464C3CD7A5D9F124E603A91BEC3255C245CA37DEBAF59A9488B7A575AF122BA0D5958CF51129D4DE52E451555A4996E02928C2CB4697F320E43A33D0FADF8220B2A33255A77DC5B391536051428A5522FAD7146AC44B72332766991C4D408A6BCCE4626AB94E9D283F4AA4941745F4B76E849BEC34E26B15F9334795C539B5754A3FF29FCA2A5ED01EC70192A9D3846318A56DAF4B6F7B7B591C463552DED741D06ED17C3EBB0F2F94AB5DB5B16824D350EFE0B1F0AAAF07AB5A7A1AEC37C66795F26E9BCEA95365B3174E94DF0D1C4F45099CA5D21B14C79E655324A441A33C0A73800F560211A448856A79D1717B57F8185C0F1B946859BB9E6A4B32A3DCF41EEF2DE1F4B7BD5FB753F83ED3C6E55A7B771B61E4BCADA5674EB2FB32CD7D52D1E362383C4685A761EB6864286269A9576F3269DA09D9B57DB2D9D9C4E961B24E1A8A4A14A0ADA2ED5DFB52BBEF348F1AB17D3B0D11C537AAEFBCF699F18EA497502C4047918559F365ECB2E8D49F325C19EA5629F412F3A0AF354253D378CB67D97F22C5397365C19E81E319158C62C705D49735F6A2B9E22CD75AD67A078B65589AD74E2FBD5D04732788DC2CE7B9F0FD0EBE06BA8D3A6A3A2D18F827DA4AA637A40E24A52E64B830A4E767784B5F35EEEA3B2F1BD256F1AC0E4E74B9B2E0CA6A4E5A7D17DB1676258DE913C6DEFA40E0F2CEEAF7218AAFF573D03C6BDE42A62AFAECFAD262D5E2B1988B5F49E87C85C2E6D0551AD3564EAF63D11FF8A8F12FC451A552EA74A9CAFAEF08B7C6C68C3E2152492D118AFE14B524B52EC08E8E52AD68F578BFAEF6796C54FEBF43B9943117BAFF00272B0F4F3AAC56C8FA4FB3577DBBCCE52D443B393E972708C372D3D6F4BEF36C59960CBE0CE4EABD32572A4C9264165C085C00F622B808DB27702200519429E741F469F9F7781E2B2AD0D68F7A795CB34335B5BB4766CEEB1A8665C5C8D89FDD383D74E4E3FD32F4A2FF00B97F49A653E9FAED3875E9D4E5A31A4E29CFD179D7CDB259CDE8DBA3470DA69960B10FF9D0F772FC4691BA5536FC84EA1CB780C47AF87BA97E323E655FD747DD3FCC20EA39EBD3D9A3E24A33D5FA7C0E52C157F5D1F74FF301E06BFAF8FBB7F8C0EA3A9F5AFC48AA9D3E0737CC2B7AE5EEFF00EE0F015BD72F77FF00603A5CB229C46235F79CF780AFEB97BB7F8CA65932BBD3CB47DDBFC6074B0D57D08FFB23E089CA77DBF5639B47015E315175A0ECAD774DECD1CFB6C1BC257F5B0F76FE1500DF2ABD3DEFEB691954BED5C1187CCF11EB69FBA97E611784AEFF009B0F752FCC22B64AAF4F890553A571BF8999E06BFAE8765197E615BC056F5D0F752FCC036F2DD3F0F00E50E73C056F5F0F752FCC1799575FCF8FB97F8C0E9E7ED219F9F28C16D777FED5A5FCBB4C0F0D5D2FFCD07FFADAFF00ECE8643C3E6C2A4A52529E7383B2B249252D0AEF5E72E0545D8AABADB2592E1E8B9739DFB1685F17DA62C44B3E4A2B6BCDBEBEB6762942C925A968463296F185F02E8B2A8A2C465A58992B95A248827701080F69742BA219C2CE36CA7742BA2170B8165D1C9CBF46F0CF4B5687F07C5F79D2B8A4F601F3AC342D89849EA4A7FD8CD9567A75FC4F4F5F24506A5E824DE8BAD6BAB71F30CBB5254AB54A74E739284653FE279CFECC62ADBE7282ED33979756B0F16F752F472ABA3FC0A3516F4668F93EEEA12AD52E92CEB4B4675B4DB46ABDCD7FB2F1F5D5BDA5F22EF3C4D23A5CAADE1CAADFDE41F9351F5D5BDA5F220FC9F8AFE6D5F6BF41FA4F0D23AB9D4E9139F4F719659112FE654F68AA792BFD4A9ED0DE78BF9C75B5D5EAE24794E9F039F2C9DFEA54F68AA580FBF3F686F3C4D23AEA4A7D3DE2E57A8E34B03F7E7ED10782FBD2F686F3C5D23AECE7ADFE0273B1C4F32FBD2E22783FBD2E2379E1A475DA94BEB41173EA38CF07F7A5C45E65F7A5C46F3C348EBB19C42557A8E57FF9FF007A7ED7E8555327EE9CAFB2EC6F3C348EBA953124F2755B42B5F5392B7B2AFF0003C6E12A4E70729369C5B8C927BBE27B4C0E0A108287F12D2EF3D2DDF4E97B4CE3E4D973F16A964AA79CDD477B2D11F8BF876B3B106B714D345D12A52E8B44D4914A649302DCE5D249496E2A4C69816E7ADCC0AC00F4F77BC7A778DA158CB48DDEF62727BC9D852022E6F7909547BFEB81322D0B29457AEE31726F526F66C3C5640C9F1AEEB626A2BDEB45C34BB7EE659C9E8D7E9EC7CC47ACCAD42752954853B674A39AB39B4B4EF693E931E4EC04E8D2A545453CD8A4DE76B96B93D5B64DBED3131797BFE3A44D63EBECA3C9BBB927A5EDB2F890AB5E6BED3E0BE46C9D09E9D08CD3C1CDFE96F99AB629CEAB8EA8BED772BF818EBE51A8AFE97723AB2C9926533C86F45DB1653855B2956E7772F9196794AAF3DF05F23D14BC9EBEBB907E4CADB7FADC2CA7989651ABCE7C0CF3CA5579EFB8F592F2653D37EE212F25D691694F213CA1579ECADE3EAF3D9EBFF66169EFD047F65D32D94F21E7B579F2E247CF6AF3DF13D87ECBA17ECB21653C879E55E7CB8B1AC5D5E7BE27AC97930BA823E4CEEEA2594F311C4D5E732D8559BFB4F89E9179364A1E4E8B5A79BA587494DA5FC5793D6F4BD3736E0ABCF323E93D4970D1F03B8B206B5BFB8AF03902708DA59B7BB7A1B7A1BBED8F498F99373378B2C2BCF9CCB56267CE67463925EDB77962C97F5634C539D1AF3DECB15697399B964D26B2705625525CE6494E5BD9B560492C110F4C59D2E7303779900F63D6DC57139119336C9B915DC9243B00818FA8441071138162B0360579BBC798362682A2E284E2879BB47F5A808455C5985809015BA68254D165C526053C8A1726B716A04C0A79342545174A434C0A1D1DC41D1348920AA39123C922FB8220A152054FA0BF341A2A28E4C3932F684D1050A9A1BA562D5A01C9014F24270B16DC4C0AB9302CB082BA3026C00D328B1C000008880807A88CC60144C5BC0008449CD0000D896D0002A93D3C09CBEB80000ADA88A000213D808002886B2530000A63F90000D15B7A860112D852D80003DA44008122327A06014458C000FFD9', 40, 'Cosy Couch', '2020-11-13 11:12:00.000000', 'UPCOMING', null, null);