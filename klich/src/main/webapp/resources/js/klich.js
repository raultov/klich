/** JavaScript Functions
 * 
 * Author: Raul Tovar Martin
 */

// FUNCTIONS TO SUPPORT POLYLINES DRAWING


 var setArrows;


 function ArrowHandler() {
	 this.setMap(gmap);
	 // MapCanvasProjection is only available after draw has been called.
	 //  this.draw = function() {};
	 // Markers with 'head' arrows must be stored
	 this.arrowheads = [];
	 this.arrows = [];
 }

 ArrowHandler.prototype = new google.maps.OverlayView();

 // Draw is inter alia called on zoom change events.
 // So we can use the draw method as zoom change listener
 ArrowHandler.prototype.draw = function() {
	 if (this.arrowheads.length > 0) {
		 for (var i = 0, m; m = this.arrowheads[i]; i++) {
			 m.setOptions({ position: this.usePixelOffset(m.p1, m.p2) });
		 }
	 }
	 
	 for (var i = 0, m; m = this.arrows[i]; i++) {
		 //Si el nivel de zoom es inferior a 12 entonces ocultamos las flechas
		 if (gmap.getZoom() < 12) {
		 	m.setOptions({map: null});
	 	} else {
	 		// La llamada a usePixelOffset provoca que las flechas se pinten en el extremo de la línea 
	 		m.setOptions({map: gmap, position: this.usePixelOffset(m.p1, m.p2)});
	 	}
	 }
 };


 // Computes the length of a polyline in pixels
 // to adjust the position of the 'head' arrow
 ArrowHandler.prototype.usePixelOffset = function(p1, p2) {
	 var proj = this.getProjection();
	 var g = google.maps;
	 var dist = 12; // Half size of triangle icon

	 var pix1 = proj.fromLatLngToContainerPixel(p1);
	 var pix2 = proj.fromLatLngToContainerPixel(p2);
	 var vector = new g.Point(pix2.x - pix1.x, pix2.y - pix1.y);
	 var length = Math.sqrt(vector.x * vector.x + vector.y * vector.y);
	 var normal = new g.Point(vector.x/length, vector.y/length);
	 var offset = new g.Point(pix2.x - dist * normal.x, pix2.y - dist * normal.y);

	 return proj.fromContainerPixelToLatLng(offset);
 };
 
 // Returns the triangle icon object
 ArrowHandler.prototype.addIcon = function(file) {
	 var g = google.maps;
	 var icon = new g.MarkerImage("http://www.google.com/mapfiles/" + file,
			 						new g.Size(24, 24), null, new g.Point(12, 12));
	 
	 return icon;
 };
 
 // Creates markers with corresponding triangle icons
 ArrowHandler.prototype.create = function(p1, p2, mode) {
	 var markerpos;
	 var g = google.maps;
	 if (mode == "onset") markerpos = p1;
	 else if (mode == "head") markerpos = this.usePixelOffset(p1, p2);
	 else if (mode == "midline") markerpos = g.geometry.spherical.interpolate(p1, p2, .5);

	 // Compute the bearing of the line in degrees
	 var dir = g.geometry.spherical.computeHeading(p1, p2).toFixed(1);
	 // round it to a multiple of 3 and correct unusable numbers
	 dir = Math.round(dir/3) * 3;
	 if (dir < 0) dir += 240;
	 if (dir > 117) dir -= 120;
	 // use the corresponding icon
	 var icon = this.addIcon("dir_" +dir+ ".png");
	 var marker = new g.Marker({position: markerpos,
		 			map: gmap, icon: icon, clickable: false
	 });
	 
	 marker.p1 = p1;
	 marker.p2 = p2;
	 this.arrows.push(marker);
	 
	 if (mode == "head") {
		 // Store markers with 'head' arrows to adjust their offset position on zoom change
		 marker.p1 = p1;
		 marker.p2 = p2;
		 this.arrowheads.push(marker);
	 }
	 
 };
 
 ArrowHandler.prototype.load = function (points, mode) {
	for (var i = 0; i < points.length-1; i++) {
		var p1 = points[i],
		p2 = points[i + 1];
		this.create(p1, p2, mode); 
	}
};

// Draws a polyline with accordant arrow heads
function createPoly(path, mode) {
	var poly = new google.maps.Polyline({
		strokeColor: "#0000ff",
		strokeOpacity: 0.8,
		strokeWeight: 3,
		map: gmap,
		path: path });

	setArrows.load(path, mode);
	
	return poly;
}
 

// draw the arrows
function drawArrows(circles) {
	var g = google.maps;
	
	setArrows = new ArrowHandler();
	drawArrows
	var points = [];
	
	circles.resetCounter();
	var i = 0;
	
	while (circles.hasNext()) {
		var circle = circles.next();
		
		points[i] = circle.getCenter();
		i++;
	}
	
	/*
	for (var i = 0; i < circles.length; i++) {
		var circle = circles[i];
		
		points[i] = circle.getCenter();
	}
	*/
	
	/*
	var points = [
	                 new g.LatLng(43.26, -80.15),
	                 new g.LatLng(43.19,-79.98),
	                 new g.LatLng(43.25,-79.67),
	                 new g.LatLng(43.10,-79.46),
	                 new g.LatLng(43.20,-79.23)
	               ];
	
	createPoly(points, "onset");
	
	var points2 = "og~fG~~ggNowH}oRowHacmA_pR_yFozDo|k@~uJ_{m@owHoe`@";
	var decPoints = g.geometry.encoding.decodePath(points2);
	 */
	
	createPoly(points, "midline");
	/*
	var points3 = [
	               new g.LatLng(43.477,-79.7),
	               new g.LatLng(43.55,-79.60),
	               new g.LatLng(43.642,-79.73),
	               new g.LatLng(43.51,-79.95)
	               ];

	g.event.addListenerOnce(gmap, "tilesloaded", function() {
		createPoly(points3, "head");
	});	
	*/
};

/**
 * MyHash es un tipo de objeto hash
 * 
 */

function MyHash() {
    this.current = 0;
    this.keys = new Array();
    this.values = {};
};

MyHash.prototype.put = function(key, value) {
	this.keys.push(key);
	this.values[key] = value;
};

MyHash.prototype.resetCounter = function() {
	this.current = 0;
};

MyHash.prototype.next = function() {
	var ret = null;
	
	if (this.current < this.keys.length) {
		ret = this.values[this.keys[this.current]];
		this.current++;
	}
	
	return ret;
};

MyHash.prototype.get = function(key) {
	return this.values[key];
};

MyHash.prototype.size = function() {
	return this.keys.length;
};

MyHash.prototype.hasNext = function() {
	if (this.current < this.keys.length) {
		return true;
	} else {
		return false;
	}
};

MyHash.prototype.clear = function() {
	this.current = 0;
	this.keys = new Array();
	this.values = {};
};









