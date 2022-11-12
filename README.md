

<div style="text-align: justify">

<div style="text-align: center">

# SIMPLE JAVA LOGGER

</div>

Lisenced under **MIT**, created by **Pedro Marín Sanchis**.

---

### -- HOW TO USE --

First of all, before logging anything, **the logger has to be initialized**. This can be done by using the **initialize()** method.

<pre><code>Logger.initialize();</code></pre>

This logger is not meant to be instanced.

#### · CUSTOMIZING A HEADER TEXT

You can change the header text for newly created log files using the **setHeaderText()** method.

<pre><code>Logger.setHeaderText("HEADER TEXT");</code></pre>

#### · Log types

Logs can be of various types, which come with specific properties that change their apprearance in the final log file. All log types except LogType.REQUIRED have a boolean flag declared at class level to decide if they are logged or not.

|                  |      **HEADER**     |     **WARNING**     |       **INFO**      |      **DEBUG**      | **REQUIRED** |
|:----------------:|:-------------------:|:-------------------:|:-------------------:|:-------------------:|:------------:|
| **isLogged**     | Default set to true | Default set to true | Default set to true | Default set to true | Always true  |
| **isTagShown**   | false               | true                | true                | true                | true         |
| **includesTime** | false               | true                | true                | true                | true         |

Default log types have the following apperance, note that LogType.HEADER has neither a date nor a type label.


<pre><code>Test
[2022-11-12_00-41-06]  [WARNING] : Test
[2022-11-12_00-41-06]     [INFO] : Test
[2022-11-12_00-41-06]    [DEBUG] : Test
[2022-11-12_00-41-06] [REQUIRED] : Test</code></pre>

### -- HOW TO MODIFY --

#### · Changing default file paths

#### · Adding log types

</div>