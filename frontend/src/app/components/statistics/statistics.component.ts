import {ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {PluginServiceGlobalRegistrationAndOptions} from "ng2-charts";
import {StatisticsService} from "../../services/statistics.service";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  @ViewChild("myRatio") elementView: ElementRef;
  public doughnutChartWinsLabels: string[] = [];
  public doughnutChartBidsLabels: string[] = [];
  public showBidsChart: boolean = true;
  public showBidsStatistics: boolean = true;
  public showWinsChart: boolean = true;
  public showRatioChart: boolean = true;
  public doughnutChartWinsData: number[] = [];
  public doughnutChartBidsData: number[] = [];
  public doughnutChartRatioData: number[] = [];
  public doughnutChartRatioLabels: string[] = [];
  public doughnutChartType = 'doughnut';
  public myRatio:string="";

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    legend: false
  };

  public barChartType = 'bar';
  public barChartLegend: boolean;
  //bar chart popularity
  public barChartPopularityLabels: string[] = [];
  public barChartPopularityData: number[] = [];
  public showPopularity: boolean = true;
  //bar chart success
  public barChartSuccessLabels: string[] = [];
  public barChartSuccessData: number[] = [];
  public showSuccess: boolean = true;
  public showAuctionStatistics: boolean = true;
  public doughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[]=[];
  public chartColorsTwo: any[] = [
    {
      backgroundColor: ["#6FC8CE", "#ffffff"]
    }];
  public chartColors: any[] = [
    {
      backgroundColor: ["#be5bac", "#7e46c4", "#4b5db7",
        "#3da2bf", "#47b787", "#519240",
        "#fffc51", "#fa7d00", "#ff0000", "#f8006c"]
    }];
  public options: any = {
    legend: {position: 'right'}
  }

  constructor(private statisticsService: StatisticsService, private cdRef: ChangeDetectorRef) {
    console.log("HELLOOO CONSTRUCTOR")


  }

  ngOnInit(): void {
    console.log("HELLOOO ngOnInit")

    // get Bids statistics
    //  get Bids per category
    this.statisticsService.getBidStatistics().subscribe(x => {
      this.doughnutChartBidsLabels = Object.keys(x)
      console.log("keys1 " + this.doughnutChartBidsLabels)
      this.doughnutChartBidsData = Object.values(x)
      console.log("values1 " + this.doughnutChartBidsData)
      if (this.doughnutChartBidsLabels.length != 0) {
        this.showBidsChart = false;
      } else {
        this.showBidsChart = true;

      }

    }, error => {
      this.showBidsChart = true;
    })
    //  get Wins per category
    this.statisticsService.getWinStatistics().subscribe(x => {

      this.doughnutChartWinsLabels = Object.keys(x)
      console.log("keys1 " + this.doughnutChartWinsLabels)
      this.doughnutChartWinsData = Object.values(x)
      console.log("values1 " + this.doughnutChartWinsData)
      if (this.doughnutChartWinsLabels.length != 0) {
        this.showWinsChart = false;
      } else {
        this.showWinsChart = true;
      }

    }, error => {
      this.showWinsChart = true;
    })
    //  get Wins-Bids ratio
    this.statisticsService.getBidWinRatio().subscribe(x => {
      this.doughnutChartRatioData = Object.values(x)
      this.doughnutChartRatioLabels = Object.keys(x)
      console.log("ratio" + this.doughnutChartRatioData)
      console.log("ratio length" + this.doughnutChartRatioData.length)
      this.myRatio=this.doughnutChartRatioData[0].toFixed(2)+'%';
      if (this.doughnutChartRatioData.length != 0) {
        this.showRatioChart = false;
      } else {
        this.showRatioChart = true;

      }
      this.setPlugin(x.get('wins')+'');
    }, error => {
      this.showRatioChart = true;
    })
    this.showBidsStatistics = this.showRatioChart && this.showWinsChart && this.showBidsChart;
    //Get Auction Statistics
    //  get Auction popularity per category
    this.statisticsService.getAuctionPopularity().subscribe(x => {

      this.barChartPopularityLabels = Object.keys(x)
      console.log("keys1 " + this.doughnutChartWinsLabels)
      this.barChartPopularityData = Object.values(x)
      console.log("values1 " + this.doughnutChartWinsData)
      if (this.barChartPopularityLabels.length != 0) {
        this.showPopularity = false;
        console.log("pop false")
      } else {
        this.showPopularity = true;
        console.log("pop true")

      }

    }, error => {
      this.showSuccess = true;
    })
    //  get Auction success per category
    this.statisticsService.getSuccessOfAuctions().subscribe(x => {

      this.barChartSuccessLabels = Object.keys(x)
      console.log("keys1 " + this.doughnutChartWinsLabels)
      this.barChartSuccessData = Object.values(x)
      console.log("values1 " + this.doughnutChartWinsData)
      console.log("succ data " + this.barChartSuccessData)
      if (this.barChartSuccessData.length != 0) {
        this.showSuccess = false;
        console.log("succ false")
      console.log("succ data " + this.barChartSuccessData)
      } else {
        this.showSuccess = true;
        console.log("succ true")

      }

    }, error => {
      this.showSuccess = true;
      console.log("succ error true")

    })
    console.log("succ what " + this.showSuccess)
    this.cdRef.detectChanges();
    this.elementView.nativeElement.refresh;
    console.log("succ what " + this.showSuccess)
  }

  setPlugin(percentage:string){
    this.doughnutChartPlugins=[{
      afterDraw(chart) {
        const ctx = chart.ctx;
        var txt1 = 'Success';
        var txt2= percentage+'%'

        //Get options from the center object in options
        const sidePadding = 60;
        const sidePaddingCalculated = (sidePadding / 100) * (chart.width * 2)

        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        const centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
        const centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);

        //Get the width of the string and also the width of the element minus 10 to give it 5px side padding

        const stringWidth = ctx.measureText(txt1).width;
        const elementWidth = (chart.width * 2) - sidePaddingCalculated;

        // Find out how much the font can grow in width.
        const widthRatio = elementWidth / stringWidth;
        const newFontSize = Math.floor(30 * widthRatio);
        const elementHeight = (chart.height * 2);

        // Pick a new font size so it will not be larger than the height of label.
        const fontSizeToUse = 30;
        ctx.font = fontSizeToUse + 'px Arial';
        ctx.fillStyle = 'black';

        // Draw text in center
        ctx.fillText(txt2, centerX, centerY - 10);
        var fontSizeToUse1 = 15;
        ctx.font = fontSizeToUse1 + 'px Arial';
        ctx.fillText(txt1, centerX, centerY + 10);
      }
    }];
  }

}
