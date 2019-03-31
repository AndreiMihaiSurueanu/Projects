using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityStandardAssets.Vehicles.Car;

public class CarControlActive : MonoBehaviour {

    public GameObject car_1_Control;
    public GameObject car_2_Control;
	
	void Start () {
        car_1_Control.GetComponent<CarUserControl_Pl1>().enabled = true;
        car_2_Control.GetComponent<CarUserControl_Pl2>().enabled = true;
	}
	
}
