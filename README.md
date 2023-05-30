

<div style="text-align: justify">

<div style="text-align: center">

# SIMPLE JAVA LOGGER

</div>

Lisenced under **MIT**, created by **Pedro Marín Sanchis**.
    
**READ LICENSE BEFORE USE.**

---
    
### DOWNLOAD
    
Head to the [releases](https://github.com/pedro09pm/SimpleJavaLogger/releases) page of this repository, where you'll find all the different versions.

### HOW TO USE

First of all, before logging anything, **you must create a Logger object**. This can be done by using the **constructor**:

```java
Logger logger = new Logger("Log Path", "Header Text");
```

The default path for logs is "**logs/**". They will look like so:

```
Log[2022-11-12_00-41-06][1].log
Log[2022-11-12_04-37-16][1].log
```

To log something use the **log()** method:

```java
logger.log("Your log text here", Logger.LogType.INFO);
```

**REMEMBER TO CLOSE THE LOGGER BEFORE PROGRAM EXIT.** Otherwise you could encounter your logs missing entries, or not existing at all. By default the logger will write to file every **100** entries.

Use the **close()** method.
```java
logger.close();
```

You can also use the **writeToFile()** method to force the logger to write to the file.

```java
logger.writeToFile();
```

Finally, if you want to change the automatic logging interval you can use the **setWriteToFileInterval()** method.
```java
logger.setWriteToFileInterval(50);
```

### LOGS

#### · LOG TYPES

Logs can be of various types, which come with specific properties that change their apprearance in the final log file. All log types except LogType.REQUIRED have a boolean flag declared at class level which can be changed to decide if they are logged or not.

|                  |      **HEADER**     |     **WARNING**     |       **INFO**      |      **DEBUG**      | **REQUIRED** |
|:----------------:|:-------------------:|:-------------------:|:-------------------:|:-------------------:|:------------:|
| **isTagShown**   | false               | true                | true                | true                | true         |
| **includesTime** | false               | true                | true                | true                | true         |

Default log types have the following apperance, note that LogType.HEADER has neither a date nor a type label.


```
Test
[2022-11-12_00-41-06]  [WARNING] : Test
[2022-11-12_00-41-06]     [INFO] : Test
[2022-11-12_00-41-06]    [DEBUG] : Test
[2022-11-12_00-41-06] [REQUIRED] : Test
```

#### · DELETING OLD LOG FILES

To delete old log files you can use the **deleteOldLogs()** method.

```java
Logger.deleteOldLogs("Log Path");
```

This will delete all files ending with ".log" inside the specified directory.

#### · DEACTIVATING LOGGING FOR SPECIFIC TYPES:

Say you want to make it so LogType.DEBUG logs are not logged. Use the **changeLogTypeLogging()** method.

```java
Logger.changeLogTypeLogging(Logger.LogType.DEBUG, false);
```

---

### HOW TO MODIFY

#### · ADDING LOG TYPES

Inside Logger.java you will find the enum "LogType":

```java
public static enum LogType {
    // LogType(isShown, includesTime).
    HEADER(false, false),
    WARNING(true, true),
    INFO(true, true),
    DEBUG(true, true),
    REQUIRED(true, true);

    private final boolean isTagShown;
    private final boolean includesTime;

    private LogType (boolean isTagShown, boolean includesTime) {
        this.isTagShown = isTagShown;
        this.includesTime = includesTime;
    }
}
```

Adding new log types is simple, it can be done by adding an element to the list like so:

```java
public static enum LogType {
    // LogType(isShown, includesTime).
    HEADER(false, false),
    WARNING(true, true),
    INFO(true, true),
    DEBUG(true, true),
    REQUIRED(true, true),
    NEWTYPE(true, true); // <----- Your new log type!
    ...
```

Keep in mind that the last element of the list must end with ";" and the rest of the elements must be separated by commas. Spacing for formatting and such is calculated automatically.

</div>
