using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AnalogueSpeedConverter1 : MonoBehaviour {


    static float minAngle = 205.0f;
    static float maxAngle = -70.0f;
    static AnalogueSpeedConverter1 thisSpeed;
	// Use this for initialization
	void Start () {
        thisSpeed = this;
	}
	
	public static void ShowSpeed (float speed, float min, float max)
    {
        float ang = Mathf.Lerp(minAngle, maxAngle, Mathf.InverseLerp(min, max, speed));
        thisSpeed.transform.eulerAngles = new Vector3(0, 0, ang);
    }
}
