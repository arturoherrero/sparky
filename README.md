# sparky

### ▁▂▃▅▂▇ in your shell. Groovy flavoured!

Generates sparklines for a set of data.

An alternative implementation for **[spark]** powered by Groovy.

sparky takes a comma-separated list of data and then prints a sparkline out of it.

Examples:

    sparky 1 5 22 13 53
    ===> ▁▁▃▂▇

    sparky 0,30,55,80,33,150
    ===> ▁▂▃▅▂▇

    sparky help
    ===> Prints the sparky help text


## install

sparky is a [Groovy script]. We can write unix scripts with Groovy and execute them directly on the command line as if they were normal unix shell scripts. Providing you have installed the [Groovy binary distribution] and `groovy` is on your `$PATH`. Then to run the script from the command line, drop it somewhere and make sure it's added to your `$PATH`.


## usage

Just run `sparky` and pass it a list of numbers (comma-delimited, spaces, whatever you'd like). It's designed to be used in conjunction with other scripts that can output in that format.

    $ sparky 0,30,55,80,33,150
    ▁▂▃▄▂█

    $ echo 9 13 5 17 1 | sparky
    ▄▆▂█▁


## cooler usage

There's a lot of stuff you can do.

Number of commits to the github/groovy-core Git repository, by author:

    $ git shortlog -s |
    > awk '{print $1}' |
    > sparky
    ▁▁▁▁▁▁▁▁▁▁▁▃▁▁▁▁▁▁▁▁▁▁▁▁▂▁▁▄▁▁▁▁▁▁▁▅▁▁▁▁▁▁▁▁▁▁▁▁█▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁

Code visualization. The number of characters of `sparky` itself, by line, ignoring empty lines:

    $ awk '{ print length($0) }' sparky |
    > grep -v 0 |
    > sparky
    ▂▂▁▁▃▁▃▂▃▃▃▂▁▁▂▄▁▄▅▅█▅▂▁▁▃▃▅▁▁▃▂▁▁▇▃▁


[spark]: https://github.com/holman/spark
[Groovy script]: https://github.com/arturoherrero/sparky/blob/master/sparky
[Groovy binary distribution]: http://groovy.codehaus.org/Download
