# Simple Java Logger

Lisenced under MIT, created by Pedro Marín.

## -- HOW TO USE --

### · Log types:

Logs can be of various types, which come with specific properties that change their apprearance in the final log file.

| | HEADER | WARNING  | INFO | DEBUG | REQUIRED |
---|---|---|---|---|---|
|isLogged|Default set to true|Default set to true|Default set to true|Default set to true|Always true|
|isTagShown|false|true|true|true|true|
|includesTime|false|true|true|true|true|
|padding| ---- |1|4|3|0

The "padding" attribute in a log type should always be the length of the longest type minus it's length. The only exception is where 

## -- HOW TO MODIFY

### · Changing file paths:
### · Adding log types:
