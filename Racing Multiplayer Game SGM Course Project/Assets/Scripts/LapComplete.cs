using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LapComplete : MonoBehaviour
{

    public GameObject LapCompleteTrig;
    public GameObject HalfLapTrig;

    public GameObject MinuteDisplay;
    public GameObject SecondDisplay;
    public GameObject MilliDisplay;

    public GameObject LapTimeBox;

    public GameObject lapCounter;
    public int lapsDone;

    public float rawTime;

    public GameObject raceFinish;

    private void Update()
    {
        if (lapsDone == 2)
        {
            raceFinish.SetActive(true);
        }
    }

    void OnTriggerEnter()
    {
        lapsDone += 1;
        rawTime = PlayerPrefs.GetFloat("RawTime");
        if (LapTimeManager.rawTime <= rawTime)
        {
            MilliDisplay.GetComponent<Text>().text = "" + LapTimeManager.MilliCount.ToString("F0");

            if (LapTimeManager.SecondCount <= 9)
            {
                SecondDisplay.GetComponent<Text>().text = "0" + LapTimeManager.SecondCount + ".";
            }
            else
            {
                SecondDisplay.GetComponent<Text>().text = "" + LapTimeManager.SecondCount + ".";
            }

            if (LapTimeManager.MinuteCount <= 9)
            {
                MinuteDisplay.GetComponent<Text>().text = "0" + LapTimeManager.MinuteCount + ":";
            }
            else
            {
                MinuteDisplay.GetComponent<Text>().text = "" + LapTimeManager.MinuteCount + ":";
            }



            PlayerPrefs.SetInt("MinSave", LapTimeManager.MinuteCount);
            PlayerPrefs.SetInt("SecSave", LapTimeManager.SecondCount);
            PlayerPrefs.SetFloat("MilliSave", LapTimeManager.MilliCount);
            PlayerPrefs.SetFloat("RawTime", LapTimeManager.rawTime);
        }
        LapTimeManager.MinuteCount = 0;
        LapTimeManager.SecondCount = 0;
        LapTimeManager.MilliCount = 0;
        LapTimeManager.rawTime = 0;
        lapCounter.GetComponent<Text>().text = "" + lapsDone;
        HalfLapTrig.SetActive(true);
        LapCompleteTrig.SetActive(false);
    }

}