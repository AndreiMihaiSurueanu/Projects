using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityStandardAssets.Vehicles.Car;

public class RaceFinish : MonoBehaviour {

    public GameObject myCar;
    public GameObject finishCam;
    public GameObject viewModes;
    public GameObject levelMusic;
    public GameObject completeTrig;
    public GameObject lapTimeManager;

    private void OnTriggerEnter()
    {
        myCar.SetActive(false);
        completeTrig.SetActive(false);
        CarController.m_Topspeed = 0.0f;
        myCar.GetComponent<CarController>().enabled = false;
        myCar.GetComponent<CarUserControl_Pl1>().enabled = false;
        myCar.SetActive(true);
        finishCam.SetActive(true);
        levelMusic.SetActive(false);
        viewModes.SetActive(false);
        lapTimeManager.SetActive(false);
    }
}
