//无聊写的时间差
function calculateDT(timeStamp){
	Date.prototype.diff = function(date){
		  return (this.getTime() - date.getTime())/(1000);
	};
	var now = new Date();
	var time = new Date(parseInt(timeStamp.year-100+2000),parseInt(timeStamp.month),parseInt(timeStamp.date),parseInt(timeStamp.hours),parseInt(timeStamp.minutes),parseInt(timeStamp.seconds));
	var distanceSeconds = parseInt(now.diff(time));
	if(distanceSeconds < 10){
		return "now";
	}else if (distanceSeconds < 60) {
	    return (distanceSeconds + " seconds ago");
	}else if (distanceSeconds < 60 * 60) {
	    var distance = parseInt(distanceSeconds / 60);
	    return (distance + ((distance==1)?" minute ago":" minutes ago"));
	}else if (distanceSeconds < 60 * 60 * 24) {
	    var distance = parseInt(distanceSeconds / 60 / 60);
	    return (distance + ((distance==1)?" hour ago":" hours ago"));
	}else if (distanceSeconds < 60 * 60 * 24 * 7) {
	    var distance = parseInt(distanceSeconds / 60 / 60 / 24);
	    return (((distance==1)?"Yesterday":distance + " days ago"));
	}else if (distanceSeconds < 60 * 60 * 24 * 7 * 4) {
	    var distance = parseInt(distanceSeconds / 60 / 60 / 24 / 7);
	    return (distance + ((distance==1)?" week ago":" weeks ago"));
	}/*else if (distanceSeconds < 60 * 60 * 24 * 7 * 4 * 12) {
	    var distance = parseInt(distanceSeconds/60/60/24/7/4);
	    return (distance + ((distance==1)?" month ago":" months ago"));
	}*/else{
	    /*var distance = parseInt(distanceSeconds/60/60/27/7/4/12)
	    alert(distance + ((distance==1)?" year ago":" years ago"));*/
	    //return (parseInt(timeStamp.year-100+2000)+ "-" +parseInt(timeStamp.month+1)+ "-" +parseInt(timeStamp.date)+" "+parseInt(timeStamp.hours)+":"+parseInt(timeStamp.minutes)+":"+parseInt(timeStamp.seconds));
		//下面是不显示秒
		return (parseInt(timeStamp.year-100+2000)+ "-" +parseInt(timeStamp.month+1)+ "-" +parseInt(timeStamp.date)+" "+parseInt(timeStamp.hours)+":"+parseInt(timeStamp.minutes));
	}
};