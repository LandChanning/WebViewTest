$(function(){
    console.log("------------------------resize start------------------------");
    console.log("screen.width = " + screen.width);
    console.log("document.documentElement.offsetWidth = " + document.documentElement.offsetWidth);
    console.log("document.documentElement.clientWidth = " + document.documentElement.clientWidth + " (layout viewport width)");
    console.log("window.innerWidth = " + window.innerWidth);
    console.log("window.devicePixelRatio = " + window.devicePixelRatio);
    console.log("document.documentElement.scrollWidth = " + document.documentElement.scrollWidth);
    console.log("document.body.scrollWidth = " + document.body.scrollWidth);

	var scale = document.documentElement.clientWidth / document.body.scrollWidth;
	console.log("scale = " + scale);
	$('meta[name="viewport"]').attr('content','width=device-width, initial-scale=' + scale + ', minimum-scale=' + scale + ', maximum-scale=1.5, user-scalable=yes');

	console.log("------------------------resize end------------------------");
	console.log("screen.width = " + screen.width);
    console.log("document.documentElement.offsetWidth = " + document.documentElement.offsetWidth);
    console.log("document.documentElement.clientWidth = " + document.documentElement.clientWidth + " (layout viewport width)");
    console.log("window.innerWidth = " + window.innerWidth);
    console.log("window.devicePixelRatio = " + window.devicePixelRatio);
    console.log("document.documentElement.scrollWidth = " + document.documentElement.scrollWidth);
    console.log("document.body.scrollWidth = " + document.body.scrollWidth);
});