export class AuctionPostModel {
  id: number;
  creatorId: number;
  name: string;
  category: string;
  startTime: Date;
  endTime: Date;
  // location: string;
  minPrice: number;
  description: string;
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
