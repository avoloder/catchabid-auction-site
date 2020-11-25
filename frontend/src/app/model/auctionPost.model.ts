export class AuctionPost {
  id: number;
  creatorName: string;
  creatorId: number;
  category: string;
  status: string;
  auctionName: string;
  auctionDescription: string;
  startTime: Date;
  endTime: Date;
  minPrice: number;
  highestBid: number;
  image: string;

  constructor(id: number, creatorName: string, creatorId: number, category: string, status: string, auctionName: string, auctionDescription: string, startTime: Date, endTime: Date, minPrice: number, highestBid: number, image: string) {
    this.id = id;
    this.creatorName = creatorName;
    this.creatorId = creatorId;
    this.category = category;
    this.status = status;
    this.auctionName = auctionName;
    this.auctionDescription = auctionDescription;
    this.startTime = startTime;
    this.endTime = endTime;
    this.minPrice = minPrice;
    this.highestBid = highestBid;
    this.image = image;
  }
}
