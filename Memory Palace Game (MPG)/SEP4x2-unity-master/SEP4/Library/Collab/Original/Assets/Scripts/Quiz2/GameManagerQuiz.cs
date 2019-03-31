using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameManagerQuiz : MonoBehaviour {

    public Text scoreDisplayText;
    public Text highScoreText;


    public GameObject question1Display;
    public GameObject question2Display;
    public GameObject question3Display;
    public GameObject question4Display;
    public GameObject question5Display;
    public GameObject roundEndDisplay;
    public Button buttonTrue;
    public Button buttonFalse;

    private int playerScore;
    private int highScore=0;
  

    void Start()
    {

        playerScore = 0;

        highScore = PlayerPrefs.GetInt("score");
        highScoreText.text = PlayerPrefs.GetInt("score").ToString();

 
    }


    public void Question1Correct()
    {
            playerScore = playerScore + 10;
            scoreDisplayText.text = "Score: " + playerScore.ToString();

            question1Display.SetActive(false);
            question2Display.SetActive(true);
     }

    public void Question1False()
    {
        question1Display.SetActive(false);
        question2Display.SetActive(true);
    }

    public void Question2Correct()
    {
        playerScore = playerScore + 10;
        scoreDisplayText.text = "Score: " + playerScore.ToString();

        question2Display.SetActive(false);
        question3Display.SetActive(true);
    }

    public void Question2False()
    {
        question2Display.SetActive(false);
        question3Display.SetActive(true);
    }

    public void Question3Correct()
    {
        playerScore = playerScore + 10;
        scoreDisplayText.text = "Score: " + playerScore.ToString();

        question3Display.SetActive(false);
        question4Display.SetActive(true);
    }

    public void Question3False()
    {
        question3Display.SetActive(false);
        question4Display.SetActive(true);
    }

    public void Question4Correct()
    {
        playerScore = playerScore + 10;
        scoreDisplayText.text = "Score: " + playerScore.ToString();

        question4Display.SetActive(false);
        question5Display.SetActive(true);
    }

    public void Question4False()
    {
        question4Display.SetActive(false);
        question5Display.SetActive(true);
    }

    public void Question5Correct()
    {
        playerScore = playerScore + 10;
        scoreDisplayText.text = "Score: " + playerScore.ToString();

        question5Display.SetActive(false);
        roundEndDisplay.SetActive(true);
    }

    public void Question5False()
    {
        question5Display.SetActive(false);
        roundEndDisplay.SetActive(true);
    }


    public void ReturnToMenu()
    {
        if (playerScore > highScore)
        {
            highScore = playerScore;
            highScoreText.text = highScore.ToString();

            PlayerPrefs.SetInt("score", highScore);
        }
                
        SceneManager.LoadScene("MainMenu");
    }

}
