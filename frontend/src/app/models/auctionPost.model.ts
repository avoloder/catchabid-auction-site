export class AuctionPostModel {
  id: number;
  name: string;
  creatorName: string;
  creatorId: number;
  category: string;
  status: string;
  auctionName: string;
  description: string;
  startTime: Date;
  endTime: Date;
  country: string;
  city: string;
  address: string;
  houseNr: number;
  minPrice: number;
  highestBid: number;
  image: string;

  constructor(creatorId: number, name: string, category: string, startTime: Date, endTime: Date, country: string,
              city: string, address: string, houseNr: number, minPrice: number, description: string, image: string) {
    this.creatorId = creatorId;
    this.name = name;
    this.category = category;
    this.startTime = startTime;
    this.endTime = endTime;
    this.country = country;
    this.city = city;
    this.address = address;
    this.houseNr = houseNr;
    this.minPrice = minPrice;
    this.description = description;
    this.image = image;
  }
}
