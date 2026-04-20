clang-tidy - objc-nsdate-formatter

</div>

# objc-nsdate-formatter

When `NSDateFormatter` is used to convert an `NSDate` type to a `String`
type, the user can specify a custom format string. Certain format
specifiers are undesirable despite being legal. See
<http://www.unicode.org/reports/tr35/tr35-dates.html#Date_Format_Patterns>
for all legal date patterns.

This checker reports as warnings the following string patterns in a date
format specifier:

1.  yyyy + ww : Calendar year specified with week of a week year (unless
    YYYY is also specified).
    - **Example 1:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">yyyy-ww</span>;  
      Output string: <span class="title-ref">2014-01</span> (Wrong
      because it’s not the first week of 2014)

    - **Example 2:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">dd-MM-yyyy
      (ww-YYYY)</span>;  
      Output string: <span class="title-ref">29-12-2014 (01-2015)</span>
      (This is correct)
2.  F without ee/EE : Numeric day of week in a month without actual day.
    - **Example:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">F-MM</span>;  
      Output string: <span class="title-ref">5-12</span> (Wrong because
      it reads as *5th \_\_\_ of Dec* in English)
3.  F without MM : Numeric day of week in a month without month.
    - **Example:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">F-EE</span>  
      Output string: <span class="title-ref">5-Mon</span> (Wrong because
      it reads as *5th Mon of \_\_\_* in English)
4.  WW without MM : Week of the month without the month.
    - **Example:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">WW-yyyy</span>  
      Output string: <span class="title-ref">05-2014</span> (Wrong
      because it reads as *5th Week of \_\_\_* in English)
5.  YYYY + QQ : Week year specified with quarter of normal year (unless
    yyyy is also specified).
    - **Example 1:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">YYYY-QQ</span>  
      Output string: <span class="title-ref">2015-04</span> (Wrong
      because it’s not the 4th quarter of 2015)

    - **Example 2:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">ww-YYYY
      (QQ-yyyy)</span>  
      Output string: <span class="title-ref">01-2015 (04-2014)</span>
      (This is correct)
6.  YYYY + MM : Week year specified with Month of a calendar year
    (unless yyyy is also specified).
    - **Example 1:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">YYYY-MM</span>  
      Output string: <span class="title-ref">2015-12</span> (Wrong
      because it’s not the 12th month of 2015)

    - **Example 2:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">ww-YYYY
      (MM-yyyy)</span>  
      Output string: <span class="title-ref">01-2015 (12-2014)</span>
      (This is correct)
7.  YYYY + DD : Week year with day of a calendar year (unless yyyy is
    also specified).
    - **Example 1:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">YYYY-DD</span>  
      Output string: <span class="title-ref">2015-363</span> (Wrong
      because it’s not the 363rd day of 2015)

    - **Example 2:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">ww-YYYY
      (DD-yyyy)</span>  
      Output string: <span class="title-ref">01-2015 (363-2014)</span>
      (This is correct)
8.  YYYY + WW : Week year with week of a calendar year (unless yyyy is
    also specified).
    - **Example 1:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">YYYY-WW</span>  
      Output string: <span class="title-ref">2015-05</span> (Wrong
      because it’s not the 5th week of 2015)

    - **Example 2:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">ww-YYYY
      (WW-MM-yyyy)</span>  
      Output string: <span class="title-ref">01-2015 (05-12-2014)</span>
      (This is correct)
9.  YYYY + F : Week year with day of week in a calendar month (unless
    yyyy is also specified).
    - **Example 1:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String:
      <span class="title-ref">YYYY-ww-F-EE</span>  
      Output string: <span class="title-ref">2015-01-5-Mon</span> (Wrong
      because it’s not the 5th Monday of January in 2015)

    - **Example 2:** Input Date: <span class="title-ref">29 December
      2014</span> ; Format String: <span class="title-ref">ww-YYYY
      (F-EE-MM-yyyy)</span>  
      Output string: <span class="title-ref">01-2015
      (5-Mon-12-2014)</span> (This is correct)
