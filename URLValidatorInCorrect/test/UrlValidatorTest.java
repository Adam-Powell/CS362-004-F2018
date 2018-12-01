
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import java.lang.String;
import java.util.concurrent.ThreadLocalRandom; //for random number
import java.lang.System;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	   
   }
   
   
   public void testYourFirstPartition()
   {

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing

   }
   //You need to create more test cases for your Partitions if you need to
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing
      int numtests = 20; //number of tests to run
      List<Pair<String, Boolean>> tests = new ArrayList<>(); //string: URL, bool: bad (false)/good (true) URL

      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

      List<Pair<String, Boolean>> testUrlScheme = new ArrayList<>();

      testUrlScheme.add(new Pair<>("http://", true));
      testUrlScheme.add(new Pair<>("ftp://", true));
      testUrlScheme.add(new Pair<>("h3t://", true));
      testUrlScheme.add(new Pair<>("http:/", false));
      testUrlScheme.add(new Pair<>("p:/", false));
      testUrlScheme.add(new Pair<>("hp://", false));
      testUrlScheme.add(new Pair<>("://", false));

      List<Pair<String, Boolean>> testUrlAuthority = new ArrayList<>();

      testUrlAuthority.add(new Pair<>("www.google.com", true));
      testUrlAuthority.add(new Pair<>("google.com", true));
      testUrlAuthority.add(new Pair<>("192.192.192.192", true));
      testUrlAuthority.add(new Pair<>("0.0.0.0", true));
      testUrlAuthority.add(new Pair<>("120.24.5.183", false));
      testUrlAuthority.add(new Pair<>("2.1.3.4.6", false));
      testUrlAuthority.add(new Pair<>("abc.", false));
      testUrlAuthority.add(new Pair<>("sdv", false));

      List<Pair<String, Boolean>> testUrlPort = new ArrayList<>();

      testUrlPort.add(new Pair<>(":80", true));
      testUrlPort.add(new Pair<>(":65535", true));
      testUrlPort.add(new Pair<>(":0", true));
      testUrlPort.add(new Pair<>(":68535", false));
      testUrlPort.add(new Pair<>(":-1", false));
      testUrlPort.add(new Pair<>(":68abc", false));

      List<Pair<String, Boolean>> testUrlPath = new ArrayList<>();

      testUrlPath.add(new Pair<>("/test1", true));
      testUrlPath.add(new Pair<>("/abc/123", true));
      testUrlPath.add(new Pair<>("/$abc", true));
      testUrlPath.add(new Pair<>("/../", false));
      testUrlPath.add(new Pair<>("/test1//abc", false));
      testUrlPath.add(new Pair<>("/#/test", false));
      testUrlPath.add(new Pair<>("/file[/]", false));

      List<Pair<String, Boolean>> testUrlQuery = new ArrayList<>();

      testUrlQuery.add(new Pair<>("?action=view", true));
      testUrlQuery.add(new Pair<>("?action=edit&mode=up", true));
      testUrlQuery.add(new Pair<>("", true));
      testUrlQuery.add(new Pair<>("?Action=view", true));
      testUrlQuery.add(new Pair<>("?Action={}", true));

      //creating random collection of tested strings
      /*for(int i = 0; i < numtests; i++){
         List<String> urlcomp = new ArrayList<>(); //holds each indiv url component
         String tempurl = new String(); //collects url components for main url
         Boolean goodURL = true; //true if good url, false if bad
         for(int j = 0; j < 4; j++){
            String temp = new String();
            int rand;

            switch (j){
               //scheme
               case 0:
                  rand = ThreadLocalRandom.current().nextInt(0, testUrlScheme.size());
                  temp = testUrlScheme.get(rand).getKey();
                  if (!testUrlScheme.get(rand).getValue()){
                     goodURL = false;
                  }
                  break;
               //authority
               case 1:
                  rand = ThreadLocalRandom.current().nextInt(0, testUrlAuthority.size());
                  temp = testUrlAuthority.get(rand).getKey();
                  if (!testUrlAuthority.get(rand).getValue()){
                     goodURL = false;
                  }
                  break;
               //port
               case 2:
                  rand = ThreadLocalRandom.current().nextInt(0, testUrlPort.size());
                  temp = testUrlPort.get(rand).getKey();
                  if (!testUrlPort.get(rand).getValue()){
                     goodURL = false;
                  }
                  break;
               //path
               case 3:
                  rand = ThreadLocalRandom.current().nextInt(0, testUrlPath.size());
                  temp = testUrlPath.get(rand).getKey();
                  if (!testUrlPath.get(rand).getValue()){
                     goodURL = false;
                  }
                  break;
               case 4:
                  rand = ThreadLocalRandom.current().nextInt(0, testUrlPath.size());
                  temp = testUrlQuery.get(rand).getKey();
                  if (!testUrlQuery.get(rand).getValue()){
                     goodURL = false;
                  }
                  break;
            }urlcomp.add(temp); //if we want to test individual components
            tempurl = tempurl + temp; //collect components
         }tests.add( new Pair<>(tempurl, goodURL));
      }*/

      // Generate the unit test cases

      //testing isValidScheme()
      for(int i = 0; i < testUrlScheme.size(); i++) {
         if (testUrlScheme.get(i).getValue() != urlVal.isValidScheme(testUrlScheme.get(i).getKey())) {
            System.out.println("Error testing isValidScheme(): `" + testUrlScheme.get(i).getKey() + "` expected " + (testUrlScheme.get(i).getValue() ? "TRUE" : "FALSE") + ", but found " + (urlVal.isValidScheme(testUrlScheme.get(i).getKey()) ? "TRUE" : "FALSE"));
         }
      }
      //testing isValidAuthority()
      /*for(int i = 0; i < testUrlAuthority.size(); i++) {
         if (testUrlAuthority.get(i).getValue() != urlVal.isValidAuthority(testUrlAuthority.get(i).getKey())) {
            System.out.println("Error testing isValidAuthority(): `" + testUrlAuthority.get(i).getKey() + "` expected " + (testUrlAuthority.get(i).getValue() ? "TRUE" : "FALSE") + ", but found " + (urlVal.isValidAuthority(testUrlAuthority.get(i).getKey()) ? "TRUE" : "FALSE"));
         }
      }*/
      //testing isValidPath()
      for(int i = 0; i < testUrlPath.size(); i++) {
         if (testUrlPath.get(i).getValue() != urlVal.isValidPath(testUrlPath.get(i).getKey())) {
            System.out.println("Error testing isValidPath(): `" + testUrlPath.get(i).getKey() + "` expected " + (testUrlPath.get(i).getValue() ? "TRUE" : "FALSE") + ", but found " + (urlVal.isValidPath(testUrlPath.get(i).getKey()) ? "TRUE" : "FALSE"));
         }
      }
      //testing isValidQuery()
      for(int i = 0; i < testUrlQuery.size(); i++) {
         if (testUrlQuery.get(i).getValue() != urlVal.isValidQuery(testUrlQuery.get(i).getKey())) {
            System.out.println("Error testing isValidQuery(): `" + testUrlQuery.get(i).getKey() + "` expected " + (testUrlQuery.get(i).getValue() ? "TRUE" : "FALSE") + ", but found " + (urlVal.isValidQuery(testUrlQuery.get(i).getKey()) ? "TRUE" : "FALSE"));
         }
      }


      //testing collection of different URLS
      /*for(int i = 0; i < numtests; i++){
         boolean out = urlVal.isValid(tests.get(i).getKey());
         //assertEquals(tests.get(i).getValue(), out);
         if(tests.get(i).getValue() != out)
            System.out.println("Incorrect operation: `" +  tests.get(i).getKey() + "` expected " + (tests.get(i).getValue() ? "TRUE" : "FALSE") + ", but found " + (out ? "TRUE" : "FALSE"));
      }*/
   }
   


}
