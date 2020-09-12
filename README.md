![](https://travis-ci.org/seanstory/filesystem-workplace-search-source.svg?branch=master&status=passed)
# Filesystem Custom API Source - for Elastic Workplace Search
A Custom API Source implementation for Elastic Workplace Search that crawls a filesystem for content.

### Building
To build the project, run a simple

    mvn clean install

### Installation
After building, move the tarball from `filesystem-workplace-search-source-dist/target/*.tar.gz` to wherever you wish to
install your source, and simple untar the tarball.

### Configuration
You must configure your Content Source Key, Access token, and filesystem root directory in `config/source.yaml`. You may also optionally
provide a pattern that can be used to filter which files are processed during your crawl.

### Usage
To run, simply execute:

    bin/sync filesystem
