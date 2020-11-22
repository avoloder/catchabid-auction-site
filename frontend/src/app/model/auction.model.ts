export class AuctionModel {
  id: number;
  creator: string;
  name: string;
  category: string;
  startTime: Date;
  endTime: Date;
  image: File;

  constructor(creator: string, name: string, category: string, startTime: Date, endTime: Date,
              minPrice: number, description: string, image: File) {
    this.creator = creator;
    this.name = name;
    this.category = category;
    this.startTime = startTime;
    this.endTime = endTime;
    this.image = image;
  }
}
