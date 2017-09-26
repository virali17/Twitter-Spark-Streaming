# Twitter-Spark-Streaming

This project shows how to use Spark's streaming capabilities to analyze near real-time Twitter data directly from Twitter's sample stream. We will demonstrate how to extract the particular hashtag in Twitter's sample stream over the past few seconds.

The code is written in Scala, should be compiled with Maven, and submitted to your cluster (or Spark standalone installation) using spark-submit. The code can also be executed using spark-shell.

Step 1: Setting up your environment

1. Install CLoudera Quickstart VM using Vmware or Virtualbox.
2. Enter your Twitter API Credentials.
Go to https://apps.twitter.com and look up your Twitter API Credentials, or create an app to create them.
Enter your credentials into the cell below

Step 2: Add Streaming library

Spark Streaming includes a special library for Twitter access, called spark-streaming-twitter_2.10, which will be included in our pom.xml file as a dependency (along with spark-core_2.10 and spark-streaming_2.10).

Step 3: Run your code

To build, simply run: $ mvn clean package
To run your code:
$ spark-submit --class TopHashtags --master yarn-client ./target/SparkStreamingTwitterDemo-1.0-SNAPSHOT-jar-with-dependencies.jar
