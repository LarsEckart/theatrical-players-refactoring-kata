# Theatrical Players Refactoring Kata

Image a company of theatrical players who go out to various events performing plays. 
Typically, a customer will request a few plays and the company charges them based on the size of the audience and the kind of play they perform. 
There are currently two kinds of plays that the company performs: tragedies and comedies. 
As well as providing a bill for the performance, the company gives its customers "volume credits" which they can use for discounts on future performances — think of it as a customer loyalty mechanism.

## What are your thoughts on the design of this program?

Imagine this program on a larger scale — perhaps hundreds of lines long. 
At that size, a single inline function is hard to understand.

## Changes

* We want to print also html statements.
* The players are looking to perform more kinds of plays: they hope to add history, pastoral, pastoral-comical, historical- pastoral, tragical-historical, tragical-comical-historical-pastoral, scene individable, and poem unlimited to their repertoire. 


Let me stress that it’s these changes that drive the need to perform refactoring. 
If the code works and doesn’t ever need to change, it’s perfectly fine to leave it alone. 
It would be nice to improve it, but unless someone needs to understand it, it isn’t causing any real harm. 
Yet as soon as someone does need to under- stand how that code works, and struggles to follow it, then you have to do something about it. 