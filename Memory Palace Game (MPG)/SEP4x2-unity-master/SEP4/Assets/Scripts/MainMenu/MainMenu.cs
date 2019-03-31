using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class MainMenu : MonoBehaviour
{
    private Transform cameraTransform;
    public Text score;
    private int highScore=0;
    
    private void Start()
    {
        cameraTransform = Camera.main.transform;
        score.text = PlayerPrefs.GetInt("score").ToString();
    }

    public void LookAtLevels()
    {
        cameraTransform.transform.Rotate(Vector3.left, 51.0f);
    }

    public void LookAtMainMenu()
    {
        cameraTransform.transform.Rotate(Vector3.right, 51.0f);
    }

    public void LookAtShop()
    {
        cameraTransform.transform.Rotate(Vector3.right, 51.0f);
    }

    public void ResetScore()
    {
        score.text = "0";
        PlayerPrefs.DeleteAll();
    }
}
