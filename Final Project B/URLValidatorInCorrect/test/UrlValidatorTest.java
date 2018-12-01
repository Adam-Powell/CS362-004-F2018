
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

   
   
   public void testManualTest(){
		//You can use this function to implement your manual testing   
		UrlValidator urlVal = new UrlValidator();

		// List of strings to test, taken from the tabs open in my web browser.
		int numtests = 20;
		String tests[] = new String[] {
					"https://www.google.com/",
					"https://mail.google.com/mail/u/1/?zx=4hfnoeq6lana#inbox",
					"https://mail.google.com/mail/b/ANR_UvS-oEJfNneriZARTivUbDzNwlwG7NJLfWBVkgsAZ_XZwsq8/u/0/#starred",
					"https://forum.ss13polaris.com/index.php",
					"https://github.com/PolarisSS13/Polaris/pulls",
					"http://www.astronomy.com/news",
					"https://imgur.com/a/tGhH4",
					"https://www5.whentowork.com/cgi-bin/w2wE.dll/empmessaging.htm?SID=139824062427F&Show=New",
					"http://www.byond.com/docs/ref/",
					"https://ffxiv.consolegameswiki.com/wiki/Garo",
					"http://en.ff14housing.com/itemlist.php?h=4",
					"https://ffxiv.gamerescape.com/wiki/Behemoth_Horn",
					"https://docs.google.com/document/d/1V6yCb_7q7ueehxElurCIUT1Ooudv8s_dZptJ6xvZGjY/edit#heading=h.2qtbycupymke",
					"http://web.engr.oregonstate.edu/~benl/Courses/ece375.fa18/Assignments/hw4_ece375_fa18.pdf",
					"https://stackoverflow.com/questions/14736343/java-array-declaration-in-multiple-lines",
					"https://se2-finalproject.slack.com/messages/CDY6G24QP/?",
					"https://oregonstate.instructure.com/courses/1692917/assignments/7334042",
					"https://github.com/cs362-004/CS362-004-F2018",
					"https://www.youtube.com/watch?v=7z4WJAEG3u8",
					"https://docs.oracle.com/javase/7/docs/api/java/lang/String.html"
		};
		boolean expected_out[] = new boolean[20];
		// Generate the expected outcomes for each test case
		for(int i = 0; i < numtests; i++)
			expected_out[i] = true; // All URLs are definitely valid, they're taken from working webpages

		// Run all tests and output false test cases
		for(int i = 0; i < numtests; i++){
			boolean out = urlVal.isValid(tests[i]);
			//assertEquals(expected_out[i], out);
			if(out != expected_out[i])
				System.out.println("Incorrect operation: `" +  tests[i] + "` expected " + (expected_out[i] ? "TRUE" : "FALSE") + ", but found " + (out ? "TRUE" : "FALSE"));
		}
	}
   
   
   public void testYourFirstPartition()
   {
      //Testing schemes
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

      int totalTest = 0;
      int validTest = 0;


      Pair[] testUrlScheme = {new Pair("ftp://", true),
              new Pair("https://", true),
              new Pair("h3t://", true)};
              //new Pair("http:/", false),
             // new Pair("hp//", false),
             // new Pair("://", false)};


      Pair[] testUrlAuthority = {new Pair("www.google.com", true),
              new Pair("google.com", true),
              new Pair("255.255.255.255", true),
              new Pair("0.0.0.0", true)};
             // new Pair("286.256.265.295", false),
             // new Pair("2.1.3.4.6", false),
             // new Pair("abc.", false),
             // new Pair("sdv", false)};


      Pair[] testUrlPort = {new Pair("", true),
              new Pair(":80", true),
              new Pair(":65535", true),
              new Pair(":0", true)};
             // new Pair(":68535", false),
             // new Pair(":-1", false),
              //new Pair(":68abc", false)};


      Pair[] testUrlPath = {new Pair("", true),
              new Pair("/~test1", true),
              new Pair("/abc/123", true),
              new Pair("/$abc", true)};
              //new Pair("/../", false),
              //new Pair("/test1//abc", false),
              //new Pair("//#/test", false)};

     Pair[] testList = new Pair[5000];

      for (int idScheme = 0; idScheme < testUrlScheme.length; ++idScheme)
      {

         for (int idAuth = 0; idAuth < testUrlAuthority.length; ++idAuth)
         {
            for (int idPort = 0; idPort < testUrlPort.length; ++idPort)
            {
               for (int idPath = 0; idPath < testUrlPath.length; ++idPath)
               {
                  String temp = testUrlScheme[idScheme].item;
                          temp = temp + testUrlAuthority[idAuth].item;
                          temp = temp + testUrlPort[idPort].item;
                          temp = temp + testUrlPath[idPath].item;

                  if(testUrlScheme[idScheme].valid == true
                          && testUrlAuthority[idAuth].valid == true
                          && testUrlPort[idPort].valid == true
                          && testUrlPath[idPath].valid == true) {

                      testList[totalTest] = new Pair(temp, true);
                     validTest++;
                  }
                  else
                  {
                     testList[totalTest] = new Pair(temp, false);
                  }

                  totalTest++;
               }
            }
         }
      }

      for (int idx = 0; idx < totalTest; idx++)
      {
          try {
              Boolean test = urlVal.isValid(testList[idx].item);
              Boolean known = testList[idx].valid;
              assertEquals(testList[idx].item, known, test);
          } catch(Error e)
          {
              System.out.print("Error: ");
              System.out.println(e);
          }
      }
   }
   
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
