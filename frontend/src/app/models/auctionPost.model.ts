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
  minPrice: number;
  highestBid: number;
  image: string;

  constructor(creatorId: number, name: string, category: string, startTime: Date, endTime: Date,
              minPrice: number, description: string, image: string) {
    this.creatorId = creatorId;
    this.name = name;
    this.category = category;
    this.startTime = startTime;
    this.endTime = endTime;
    this.minPrice = minPrice;
    this.description = description;
    this.image = image;
  }
}
