// Import packages
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.twitter._

object TwitterHashtag {
  def main(args: Array[String]): Unit = {
    
    // Set up the Spark configuration with our app name
    val sparkConf = new SparkConf().setAppName("TwitterHashtag")

    // Use the config to create a streaming context that creates a new RDD with a batch interval of every 5 seconds.
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    
    // Use the streaming context and the TwitterUtils to create the
    // Twitter stream.
    val stream = TwitterUtils.createStream(ssc, None)
        
    // Parse the tweets and gather the hashTags with "LamarUni" keyword.
    val hashTagStream = twitterStream.map(_.getText).flatMap(_.split(" ")).filter(_.startsWith("#LamarUni"))
    
    // Find hash tags with "Lamar University" keyword
    val specifichashTag = hashTags.
    
    // Convert hashtag to (hashtag, 1) pair for future reduction.
    val hashTagPairs = hashTagStream.map(hashtag => ("#" + hashtag.getText, 1))
    
   // Compute the counts of each hashtag by window.
  val windowedhashTagCount = hashTagPairs.reduceByKey((x: Int, y: Int) => x + y, Seconds(10))
  
  //// For each window, calculate the top hashtags for that time period.
  windowedhashTagCount.foreachRDD(hashTagCountRDD => {
    val topTags = hashTagCountRDD.top(10)
    println("\n Keywords in last 10 seconds (%s total):".format(hashTagCountRDD.count()))
      topTags.foreach{case (tag, count) => println("%s (%d tweets)".format(tag, count))}
    })
   
    // Finally, start the streaming operation and continue until killed.
    ssc.start()
    ssc.awaitTermination()
  }
}
