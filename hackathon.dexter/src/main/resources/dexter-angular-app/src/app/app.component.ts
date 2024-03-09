import { Component } from '@angular/core';
import { HostListener } from '@angular/core';
import {  Input, OnInit,  } from '@angular/core';
import { HttpClient } from '@angular/common/http';

declare var webkitSpeechRecognition: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']

})
export class AppComponent implements OnInit {

  public keypressed;
  public oldkeypressed;
  public Content : any= '';
  public recognition:any;
  public isStoppedSpeechRecog = false;
  public text = '';
  tempWords;
  public oldAnswer : any;
  questionNum : number= 0;
  question : string;
  public output;
  public name="";
  public yes="yes";
  public no="no";
  public count=1;
  public receiver;
  public receiverAPI; //make api call using this variable
  public amount;
  public phoneNum;
  public micState: Boolean=false;
  public prevQuestionNum = -1;
  questionArray:any;
  baseUrl:string = '/askDexter';
  @HostListener('window:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    this.oldkeypressed=this.keypressed;
    this.keypressed = event.keyCode;
    this.keypcall(this.keypressed);
  }
  ngOnInit() {

  if (!('webkitSpeechRecognition' in window)) {
    console.log("IT worked for me")
    }
  //  this.name= 'Megha';
    //this.questionArray = [{"question":"pet name", "answer":"lucy"}, {"question":"travel destination", "answer":"london"}, {"question":"school teacher", "answer":"maria"}]
   this.http.get<any>(this.baseUrl+'/getUser/1').subscribe(data => {
        this.name = data.firstName;
        this.phoneNum = data.phoneNum;
    });
    this.http.get<any>(this.baseUrl+'/getQuestion/1').subscribe(data => {
        this.questionArray = data;
    }) ;
    this.init();
  }
  constructor(private http: HttpClient) { }

   init() {
    this.recognition=  new webkitSpeechRecognition();
     this.recognition.continuous= true;
     this.recognition.lang = 'en-US';

     this.recognition.onerror = function(event) {
      if(event.error == 'no-speech') {
          this.tempWords = "";
          this.output="Uanble to understand , Speak again";
          this.stop();
          this.textToAudio();
          this.Content = "";
      }
    }
     this.recognition.addEventListener('result', (e) => {
       const transcript = Array.from(e.results)
         .map((result) => result[0])
         .map((result) => result.transcript)
         .join('');
       this.tempWords = transcript;
       this.Content += transcript;

     });
   }

   start() {
     this.isStoppedSpeechRecog = false;
     this.recognition.start();
     console.log("Speech recognition started")
     this.recognition.addEventListener('end', (condition) => {
       if (this.isStoppedSpeechRecog) {
         this.recognition.stop();
         console.log("End speech recognition")
       } else {
         this.wordConcat()
         this.recognition.start();
       }
     });
   }
   stop() {
     this.isStoppedSpeechRecog = true;
     this.wordConcat()
     this.recognition.stop();
     console.log("End speech recognition")
   }

   wordConcat() {
     this.text = this.text + ' ' + this.tempWords + '.';
     this.tempWords = '';
   }

   textToAudio()  {
    console.log("Speaking up");
    let msg = this.output;
    let speech = new SpeechSynthesisUtterance();
    speech.lang = "en-US";
    speech.text = msg;
    speech.volume = 1;
    speech.rate = 1;
    speech.pitch = 1;
    window.speechSynthesis.speak(speech);
  }

  bot() {
      console.log("bot called"+" question num "+this.questionNum);
      var Content =this.Content;
      if (this.questionNum == 0) {
      this.question = 'Welcome '+this.name+', Say Hello Dexter to start ?';
      this.output = this.question;
      console.log(this.output);
      this.textToAudio();
      this.prevQuestionNum==this.questionNum-1;
      }
      else if (this.questionNum == 1) {
        this.pausecomp(1000);
        console.log(this.Content.toLowerCase().replace(/\s/g, ""));
        if(this.checkUniqueWord(this.Content.toLowerCase().replace(/\s/g, ""),'hellodexter')=='1')
        {
        this.output = 'hello ' + this.name ;
        this.textToAudio();
        this.question = 'how may I help you, today ?';
        this.output = this.question;
        this.textToAudio();
        }
        else{
          this.questionNum = 0;
          this.prevQuestionNum=-1;
        }
        }

      else if (this.questionNum == 2) {
          this.pausecomp(1000);
          if(this.checkUniqueWord(this.Content,'payment')=='1')	{
          this.question = 'Please pronounce receiver mobile number for payment';
          this.output = this.question;
          this.textToAudio();	}
          else{
          this.question = 'Currently I only support payment.';
          this.output = this.question;
          this.textToAudio();
          this.questionNum=-1;
          this.prevQuestionNum=-2;
          }
          }

      else if (this.questionNum == 3) {
          this.pausecomp(1000);

          this.Content=this.filterNumbers(Content);
          this.receiver=this.Content;
          this.receiverAPI=this.Content.replace(/\s/g, "");
          console.log("reciever number is : "+this.receiverAPI);
          if(this.checkNumber(this.receiverAPI)){

          this.output = 'The number is ' + this.Content ;
          this.textToAudio();
          this.question = 'Speak the amount you want to transfer in Rupees';
          this.output = this.question;
          this.textToAudio();
          this.count=1;
      }

      else if (this.count>=0){
            this.count--;
            this.question = 'Please speak a number. Press J to start again';
            this.output = this.question;
            this.textToAudio();
            this.Content='payment';
          }
          else{

            this.questionNum=0;
            this.prevQuestionNum=-1;
            this.pausecomp(2000);
            this.question = 'Sorry limit exceeded, Press H to start again. Have a good day '+this.name+' Thank you';
            this.output = this.question;
            this.textToAudio();
            this.count=1;

          }
      }

      else if (this.questionNum == 4) {
            this.pausecomp(1000);
            this.output= 'you said '+this.Content+'Rupees' ;
            this.amount=this.Content;
            this.textToAudio();
            this.question = 'Please say yes or no to confirm';
            this.output = this.question;
            this.textToAudio();
            }

            else if (this.questionNum == 5) {
            this.pausecomp(1000);
            this.output= 'you said '+this.Content+'. Thanks for confirming';
            if( this.checkUniqueWord(this.Content.toLowerCase(), this.yes )=='1')
          { this.textToAudio();
            this.question = 'Please answer the following security questions';
            this.output = this.question;
            this.textToAudio();
            this.question = this.questionArray[0].question;
            this.output = this.question;
            this.textToAudio();
          }
          else{
            this.textToAudio();
            this.question = 'Lets record again Press J';
            this.Content=this.oldAnswer;
            this.questionNum--;
            this.prevQuestionNum=this.questionNum-1;
            this.output = this.question;
            this.textToAudio();
            this.Content=this.receiver;

          }
      }
      else if (this.questionNum == 6) {
            this.pausecomp(1000);
            if(this.checkSecurity(this.Content, this.questionArray[0].answer)=='1')
            {
              this.question = this.questionArray[1].question;
              this.output = this.question;
              this.textToAudio();
              this.count=1;
            }
            else if(this.count>0){
              this.count--;
              this.question = 'False input Press J to start again';
              this.output = this.question;
              this.Content='yes'
              this.textToAudio();

            }
            else{
              this.question = 'Sorry Verification failed';
              this.output = this.question;
              this.textToAudio();
              this.questionNum=11;
              this.pausecomp(2000);
              this.question = 'Press H to start again. Have a good day '+this.name+' Thank you';
              this.output = this.question;
              this.textToAudio();
              this.count=1;
            }
        }

        else if (this.questionNum == 7) {
              this.pausecomp(1000);
              if(this.checkSecurity(this.Content, this.questionArray[1].answer)=='1')
              {
                this.question = this.questionArray[2].question;
                this.output = this.question;
                this.textToAudio();
                this.count=1;
              }
              else if(this.count>0){
                this.count--;
                this.question = 'False input Press J to record again';
                this.output = this.question;
                this.textToAudio();
                this.Content=this.questionArray[1].answer;

              }
              else{
                this.question = 'Sorry Verification failed';
                this.output = this.question;
                this.textToAudio();
                this.questionNum=11;
                this.pausecomp(2000);
                this.question = 'Press H to start again. Have a good day '+this.name+' Thank you';
                this.output = this.question;
                this.textToAudio();
                this.count=1;
              }
          }

              else if (this.questionNum == 8) {
                this.pausecomp(1000);
                if(this.checkSecurity(this.Content, this.questionArray[2].answer)=='1')
                {
                  this.question = 'We are about to start the payment ? Should we proceed. Say yes or No';
                  this.output = this.question;
                  this.textToAudio();
                  this.count=1;
                }
                else if(this.count>0){
                  this.count--;
                  this.question = 'False input Press J to record again';
                  this.output = this.question;
                  this.textToAudio();
                  this.Content=this.questionArray[1].answer;

                }
                else{
                  this.question = 'Sorry Verification failed';
                  this.output = this.question;
                  this.textToAudio();
                  this.questionNum=11;
                  this.pausecomp(2000);
                  this.question = 'Press H to start again. Have a good day '+this.name+' Thank you';
                  this.output = this.question;
                  this.textToAudio();
                  this.count=1;
                }
          }

          else if (this.questionNum == 9) {
            this.pausecomp(1000);
            this.output= 'you said '+this.Content + 'Thanks for confirming';
            if( this.checkUniqueWord(this.Content.toLowerCase(), this.yes )=='1')
           {
            this.textToAudio();
            this.question = 'Please wait while we process the transaction' ;
            this.output = this.question;
            this.textToAudio();
            this.http.post<any>(this.baseUrl+'/makePayment', { sender: this.phoneNum, receiver:this.receiver, token: 'xyz', amount:this.amount }).subscribe(data => {
              this.question = 'Payment to '+this.receiver+'with amount'+this.amount+'is successfully completed';
              this.output = this.question;
              this.textToAudio();
              console.log("Thank you") ;
              this.pausecomp(1000);
              this.question = 'Have a good day '+this.name+' Thank you';
              this.output = this.question;
              this.textToAudio();
            });
           }
           else{
            this.textToAudio();
            this.question = 'Aborting Transaction ';
            this.output = this.question;
            this.textToAudio();
            this.questionNum=12;
            this.pausecomp(2000);
            this.question = 'Have a good day '+this.name+' Thank you';
            this.output = this.question;
            this.textToAudio();
           }
        }
        this.oldAnswer=this.Content;
  }

  timedQuestion() {
      this.output = this.question;
      this.textToAudio();
  }

  checkUniqueWord(line, word ){
  var test = line;
  if( test.indexOf(word) >= 0){
  // Found world
  return '1';
  }
  else
  return '0';
  }

  checkSecurity(word, original ){
    if( word.toLowerCase()==original.toLowerCase()){
    // Found world
    return '1';
    }
    else
    return '0';
    }

  filterNumbers(num){
    const text = num;
    var output="";
    for (let i = 0; i < text.length; i++) {
    output += text.charAt(i)+' ';
    console.log(output)
    }
  return output;
  }

  keypcall(keypressed) {
    if (keypressed == 13) {
      if(this.micState==true)
      {
      this.stop();
      this.micState=false;
      }
      if(this.Content=="" || this.Content=="xx")
      {
        this.output="Unable to understand. Give me some time to recognise and then press enter, Please Speak again";
        if(this.micState==true)
        {
        this.stop();
        this.micState=false;
        }
        this.questionNum--;
        this.prevQuestionNum=this.questionNum-1;
        this.oldAnswer=this.Content;
        this.textToAudio();
      }

      console.log("the answer is "+this.Content);
      if(this.prevQuestionNum==(this.questionNum-1))
      {this.prevQuestionNum=this.questionNum;
        this.questionNum++;
        this.bot();
      }

    }
    if (keypressed ==  74) {
      //J : Repeat the question
      if(this.oldkeypressed!=this.keypressed){
      this.questionNum--;
      this.Content=this.oldAnswer;
      this.prevQuestionNum=this.questionNum-1;
      this.bot();
      }
    }
    if (keypressed ==70) {
      //F : Start mic
      this.Content='';
      if(this.micState==false)
      {this.start();
      this.micState=true;
    }
    }
    if (keypressed == 72) {
      //H : Start app from beginning
      this.questionNum=0;
      console.log("in H");
      this.bot();
      }
    }
   pausecomp(millis)
  {
      var date = new Date();
      var curDate = null;
      do { curDate = new Date(); }
      while(curDate-date.valueOf() < millis);
  }
  checkNumber(num){
    var trigger = num,
        regexp = new RegExp('^[0-9]+$'),
        test = regexp.test(trigger);
        console.log("it is a number "+ test);
        return test;
    }
}

