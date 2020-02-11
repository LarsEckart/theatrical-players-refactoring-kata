# Theatrical Players Refactoring Kata

Image a company of theatrical players who go out to various events performing plays. 
Typically, a customer will request a few plays and the company charges them based on the size of the audience and the kind of play they perform. 
There are currently two kinds of plays that the company performs: tragedies and comedies. 
As well as providing a bill for the performance, the company gives its customers "volume credits" which they can use for discounts on future performances — think of it as a customer loyalty mechanism.

## Changes

* We want to print also html statements. 
```html
<h1>Statement for BigCo</h1>
  <table>
    <tr>
      <th>play</th>
      <th>seats</th>
      <th>cost</th>
    </tr>
    <tr>
      <td>As You Like It</td>
      <td>$330.00</td>
      <td>10</td>
    </tr>
    <tr>
      <td>Othello</td>
      <td>$400.00</td>
      <td>20</td>
    </tr>
  </table>
  <p>
    Amount owed is <em>$1,075.00</em>
  </p>
  <p>
    You earned <em>5</em> credits
  </p>
```
* The players are looking to perform more kinds of plays: they hope to add history, pastoral, pastoral-comical, historical- pastoral, tragical-historical, tragical-comical-historical-pastoral, scene individable, and poem unlimited to their repertoire. 
