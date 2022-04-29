/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:
 *     - Uses indexOf to find strings
 *     - Handles responding to simple words and phrases
 * This version uses a nested if to handle default responses.
 *
 * Code adapted from work by Laurie White for the College Board.
 *
 * @author Madeline Vande Hey
 * @version 2/7/22
 */
public class Magpie2
{
    /**
     * Get a default greeting
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }

    /*** Gives a response to a user statement
     *
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        
        if(findKeyword(statement, "no", 0) >= 0)
        {
            response = "Why so negative?";
        }
        else if(findKeyword(statement, "mother", 0) >= 0
                    || findKeyword(statement, "father", 0) >= 0
                    || findKeyword(statement, "sister", 0) >= 0
                    || findKeyword(statement, "brother", 0) >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if(findKeyword(statement, "cat", 0) >= 0
                    || findKeyword(statement, "dog", 0) >= 0
                    || findKeyword(statement, "pet", 0) >= 0
                    || findKeyword(statement, "cats", 0) >= 0
                    || findKeyword(statement, "dogs", 0) >= 0
                    || findKeyword(statement, "pets", 0) >= 0)
                    
        {
                response = "Tell me more about your pets.";        
        }
        else if(findKeyword(statement, "Mr", 0) >= 0
                    || findKeyword(statement, "Mrs", 0) >= 0
                    || findKeyword(statement, "Ms", 0) >= 0
                    || findKeyword(statement, "Mx", 0) >=0)
        {
            if(findKeyword(statement, "Mr", 0) >=0) {
                response = "He sounds like a good teacher.";
            }
            else if(findKeyword(statement, "Mrs", 0) >= 0 || findKeyword(statement, "Ms", 0) >= 0){
                response = "She sounds like a good teacher.";
            }
            else if(findKeyword(statement, "Mx", 0) >=0){
                response = "They sound like a good teacher.";
            }
        }
        else if(statement.trim().equals("")) {
            response = "What have nothing to say?";
        }
        else if(findKeyword(statement, "taylor swift", 0) >= 0) {
            response = "I love Taylor Swift! What's your favorite album of hers?";
        }
        else if(findKeyword(statement, "music", 0) >=0){
            response = "I listen to a lot of music. What kind of music do you listen to?";
        }
        else if(findKeyword(statement, "yes", 0) >=0){
            response = "Are you sure about that?";
        }
        else
        {
            response = getRandomResponse();
             }
        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 8;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
        else if(whichResponse == 4) {
            response = "I'd love to hear more...";
        }
        else if(whichResponse == 5) {
            response = "I'm invested! I need to know more...";
        }
        else if(whichResponse == 6){
            response = "Wait, could you say that again?";
        }
        else if(whichResponse == 7){
            response = "I really like talking to you!";
        }

        return response;
    }


    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no").
     *
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @param startPos
     *            the character of the string to begin the
     *            search at
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        
        // the only change to incorporate the startPos is in the line below
        int position = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

        // refinement--make sure the goal isn't part of a word
        while (position >= 0)
        {
            // find the string of length 1 before and after the word
            String before = " ", after = " ";
            if (position > 0)
            {
                before = phrase.substring(position - 1, position).toLowerCase();
            }
            if (position + goal.length() < phrase.length())
            {
                after = phrase.substring(
                        position + goal.length(),
                        position + goal.length() + 1)
                        .toLowerCase();
            }

            // if before and after aren't letters, we've found the goal word
            if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
                  && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
            {
                return position;
            }

            // the last position didn't work, so let's find
            // the next, if there is one.
            position = phrase.indexOf(goal.toLowerCase(), position + 1);
        }

        return -1;
    }

    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no"). The search
     * begins at the beginning of the string.
     *
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword(statement, goal, 0);
    }

}

