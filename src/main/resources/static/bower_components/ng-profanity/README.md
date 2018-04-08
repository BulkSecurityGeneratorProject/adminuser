#ng-profanity

This is an AngularJS profanity filter built out of [ng-gentle](https://github.com/uttesh/nggentle).

I needed a quick filter to run whenever chat messages are received to block profanity, and I needed the ability to enable or disable the filter based on user preferences, so ng-profanity was born.

Instalation
-------------
```
npm install -g bower
bower install ng-profanity
```

Usage
-------------
First, include the ng-profanity.js file after you include `angular.min.js`. Then you will need to include ng-profanity as an angular dependency:
```
var app = angular.module('myApp', ['ngProfanity']);`
```

Then, whenever you have HTML that needs cleaning, you can access the filter like:
```
{{vm.messageText | profanity:vm.flag}}
```

where `vm.flag` is whether or not you want to enable the profanity filter. If you would always like to enable the profanity filter, you can instead do the following:


```
{{vm.messageText | profanity:true}}
```
 
License
--------
(The MIT License)

Copyright (c) 2016 reidwmulkey

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
