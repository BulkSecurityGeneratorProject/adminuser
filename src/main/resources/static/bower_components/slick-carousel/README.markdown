slick
-------

[1]: <https://github.com/kenwheeler/slick>

_the last carousel you'll ever need_

#### Demo

[http://kenwheeler.github.io/slick](http://kenwheeler.github.io/slick/)

#### CDN

CDN hosted slick is a great way to get set up quick:

In your ```<head>``` add:

````
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/jquery.slick/1.4.1/slick.css"/>

// Add the slick-theme.css if you want default styling
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/jquery.slick/1.4.1/slick-theme.css"/>
````

Then, before your closing ```<body>``` tag add:

```
<script type="text/javascript" src="//cdn.jsdelivr.net/jquery.slick/1.4.1/slick.min.js"></script>
```

#### Package Managers

````
//Bower
bower install --save slick.js

//NPM
npm install slick-carousel
````

#### Contributing

PLEASE review CONTRIBUTING.markdown prior to requesting a feature, filing a pull request or filing an issue.

### Data Attribute Settings

In slick 1.4 you can now add settings using the data-slick attribute. These elements get $.slick called on them automatically if the slick-data attr is present.

Example:

```markup
<div data-slick='{"slidesToShow": 4, "slidesToScroll": 4}'>
  <div><h3>1</h3></div>
  <div><h3>2</h3></div>
  <div><h3>3</h3></div>
  <div><h3>4</h3></div>
  <div><h3>5</h3></div>
  <div><h3>6</h3></div>
</div>
```

#### Settings

Option | Type | Default | Description
------ | ---- | ------- | -----------
accessibility | boolean | true | Enables tabbing and arrow key navigation
autoplay | boolean | false | Enables auto play of slides
autoplaySpeed | int  | 3000 | Auto play change interval
centerMode | boolean | false | Enables centered view with partial prev/next slides. Use with odd numbered slidesToShow counts.
centerPadding | string | '50px' | Side padding when in center mode. (px or %)
cssEase | string |  'ease' | CSS3 easing
customPaging | function | n/a | Custom paging templates. See source for use example.
dots | boolean | false | Current slide indicator dots
dotsClass | string | 'slick-dots' | Class for slide indicator dots container
draggable | boolean | true | Enables desktop dragging
easing | string |  'linear' | animate() fallback easing
edgeFriction | integer | 0.15 | Resistance when swiping edges of non-infinite carousels
fade | boolean | false | Enables fade
arrows | boolean | true | Enable Next/Prev arrows
appendArrows | string | $(element) | Change where the navigation arrows are attached (Selector, htmlString, Array, Element, jQuery object)
appendDots | string | $(element) | Change where the navigation dots are attached (Selector, htmlString, Array, Element, jQuery object)
mobileFirst | boolean | false | Responsive settings use mobile first calculation
prevArrow | string (html|jQuery selector) | object (DOM node|jQuery object) | <button type="button" class="slick-prev">Previous</button> | Allows you to select a node or customize the HTML for the "Previous" arrow.
nextArrow | string (html|jQuery selector) | object (DOM node|jQuery object) | <button type="button" class="slick-next">Next</button> | Allows you to select a node or customize the HTML for the "Next" arrow.
infinite | boolean | true | Infinite looping
initialSlide | integer | 0 | Slide to start on
lazyLoad | string | 'ondemand' | Accepts 'ondemand' or 'progressive' for lazy load technique
pauseOnHover | boolean | true | Pauses autoplay on hover
pauseOnDotsHover | boolean | false | Pauses autoplay when a dot is hovered
respondTo | string | 'window' | Width that responsive object responds to. Can be 'window', 'slider' or 'min' (the smaller of the two).
responsive | object | null | Object containing breakpoints and settings objects (see demo). Enables settings sets at given screen width. Set settings to "unslick" instead of an object to disable slick at a given breakpoint.
slide | string | '' | Slide element query
slidesToShow | int | 1 | # of slides to show at a time
slidesToScroll | int | 1 | # of slides to scroll at a time
speed | int | 300 | Transition speed
swipe | boolean | true | Enables touch swipe
swipeToSlide | boolean | false | Swipe to slide irrespective of slidesToScroll
touchMove | boolean | true | Enables slide moving with touch
touchThreshold | int | 5 | To advance slides, the user must swipe a length of (1/touchThreshold) * the width of the slider.
useCSS | boolean | true | Enable/Disable CSS Transitions
variableWidth | boolean | false | Disables automatic slide width calculation
vertical | boolean | false | Vertical slide direction
rtl | boolean | false | Change the slider's direction to become right-to-left
waitForAnimate | boolean | true | Ignores requests to advance the slide while animating

### Events

In slick 1.4, callback methods have been deprecated and replaced with events. Use them before the initialization of slick as shown below:

```javascript
// On swipe event
$('.your-element').on('swipe', function(event, slick, direction){
  console.log(direction);
  // left
});

// On edge hit
$('.your-element').on('edge', function(event, slick, direction){
  console.log('edge was hit')
});

// On before slide change
$('.your-element').on('beforeChange', function(event, slick, currentSlide, nextSlide){
  console.log(nextSlide);
});
```

Event | Params | Description
------ | -------- | -----------
beforeChange | slick, currentSlide, nextSlide | Before slide change callback
afterChange | slick, currentSlide | After slide change callback
edge | slick, direction | Fires when an edge is overscrolled in non-infinite mode.
init | slick | When Slick initializes for the first time callback
reInit | slick | Every time Slick (re-)initializes callback
setPosition | slick | Every time Slick recalculates position
swipe | slick, direction | Fires after swipe/drag


#### Methods

Methods are called on slick instances through the slick method itself in version 1.4, see below:

```javascript
// Add a slide
$('.your-element').slick('slickAdd',"<div></div>");

// Get the current slide
var currentSlide = $('.your-element').slick('slickCurrentSlide');
```

This new syntax allows you to call any internal slick method as well:

```javascript
// Manually refresh positioning of slick
$('.your-element').slick('setPosition');
```


Method | Argument | Description
------ | -------- | -----------
slick | options : object | Initializes Slick
unslick |  | Destroys Slick
slickNext |  |  Triggers next slide
slickPrev | | Triggers previous slide
slickPause | | Pause Autoplay
slickPlay | | Start Autoplay
slickGoTo | index : int | Goes to slide by index
slickCurrentSlide |  |  Returns the current slide index
slickAdd | element : html or DOM object, index: int, addBefore: bool | Add a slide. If an index is provided, will add at that index, or before if addBefore is set. If no index is provided, add to the end or to the beginning if addBefore is set. Accepts HTML String || Object
slickRemove | index: int, removeBefore: bool | Remove slide by index. If removeBefore is set true, remove slide preceding index, or the first slide if no index is specified. If removeBefore is set to false, remove the slide following index, or the last slide if no index is set.
slickFilter | filter : selector or function | Filters slides using jQuery .filter syntax
slickUnfilter | | Removes applied filter
slickGetOption | option : string(option name) | Gets an option value.
slickSetOption | option : string(option name), value : depends on option, refresh : boolean | Sets an option live. Set refresh to true if it is an option that changes the display


#### Example

Initialize with:

```javascript
$(element).slick({
  dots: true,
  speed: 500
});
 ```

Destroy with:

```javascript
$(element).unslick();
```


#### Sass Variables

Variable | Type | Default | Description
------ | ---- | ------- | -----------
$slick-font-path | string | "./fonts/" | Directory path for the slick icon font
$slick-font-family | string | "slick" | Font-family for slick icon font
$slick-loader-path | string | "./" | Directory path for the loader image
$slick-arrow-color | color | white | Color of the left/right arrow icons
$slick-dot-color | color | black | Color of the navigation dots
$slick-dot-color-active | color | $slick-dot-color | Color of the active navigation dot
$slick-prev-character | string | '\2190' | Unicode character code for the previous arrow icon
$slick-next-character | string | '\2192' | Unicode character code for the next arrow icon
$slick-dot-character | string | '\2022' | Unicode character code for the navigation dot icon
$slick-dot-size | pixels | 6px | Size of the navigation dots


#### Dependencies

jQuery 1.7

#### License

Copyright (c) 2014 Ken Wheeler

Licensed under the MIT license.

Free as in Bacon.
