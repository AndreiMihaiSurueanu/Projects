using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using UnityEngine;

public class LevelManager : MonoBehaviour {
    
    public GameObject pauseMenu;
    public GameObject player;

    public Text timerText;

    public int timeLeft;

    private int savedValue;
    private float startTime;


    private void Start()
    {
        pauseMenu.SetActive(false);
        startTime = Time.time;
        savedValue = PlayerPrefs.GetInt("ballColor");

        StartCoroutine("LoseTime");

        ChangeColorPlayer();
    }

    private void Update()
    {
        timerText.text = ("" + timeLeft);
        
        if (timeLeft <= 0)
        {
            StopCoroutine("LoseTime");
            timerText.text = ("Time is up!");


            SceneManager.LoadScene("quizMenu");
        }
    }

    //toggle pause
    public void TogglePauseMenu()
    {
        //returns state of pause menu, 
        //changing scale 0-1
        pauseMenu.SetActive(!pauseMenu.activeSelf);
        Time.timeScale = (pauseMenu.activeSelf) ? 0 : 1;

    }
    
    public void RestartLevel()
    {
        Time.timeScale = 1;
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }

    //return to menu
    public void ToMenu()
    {
        Time.timeScale = 1;
        SceneManager.LoadScene("MainMenu");
    }

    IEnumerator LoseTime()
    {
        while (true)
        {
            if (timeLeft > 60)
            {
                yield return new WaitForSeconds(5);
                timeLeft = timeLeft - 5;
            }
            else
            {
                yield return new WaitForSeconds(1);
                timeLeft--;
            }
            
        
        }
    }

    private void ChangeColorPlayer()
    {
        if (savedValue == 6)
        {
            Material material1 = Resources.Load<Material>("Materials/mat_6");

            player.GetComponent<Renderer>().material = material1;
        }

       else if (savedValue == 1)
        {
            Material material1 = Resources.Load<Material>("Materials/Ground_Texture_Material");

            player.GetComponent<Renderer>().material = material1;
        }

        else if (savedValue == 2)
        {
            Material material1 = Resources.Load<Material>("Materials/House_color");

            player.GetComponent<Renderer>().material = material1;
        }

        else if (savedValue == 3)
        {
            Material material1 = Resources.Load<Material>("Materials/mat_2");

            player.GetComponent<Renderer>().material = material1;
        }

        else if (savedValue == 4)
        {
            Material material1 = Resources.Load<Material>("Materials/mat_3");

            player.GetComponent<Renderer>().material = material1;
        }

        else if (savedValue == 5)
        {
            Material material1 = Resources.Load<Material>("Materials/mat_4");

            player.GetComponent<Renderer>().material = material1;
        }

        else if (savedValue == 7)
        {
            Material material1 = Resources.Load<Material>("Materials/mat_5");

            player.GetComponent<Renderer>().material = material1;
        }

        else if (savedValue == 8)
        {
            Material material1 = Resources.Load<Material>("Materials/mat_7");

            player.GetComponent<Renderer>().material = material1;
        }



    }

}
