using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraMovement : MonoBehaviour {

    //object we are looking at
    public Transform lookFor;

    private Vector3 wantedPosition;
    private Vector3 ySet;

    private float yOffset = 1.0f;

    private float timeStart = 0;
    
    //camera in the back of the ball
    private void Start()
    {
        //position of camera behind caracter
        ySet = new Vector3(0, yOffset, -1f * 5.0f);
        timeStart = Time.time;

       // cameraTransform = Camera.main.transform;
    }
    

    private void FixedUpdate()
    {

        if (Time.time - timeStart < 3.0f)
            return;

        //follow the player
        wantedPosition = lookFor.position + ySet;
        //smooth camera change
        transform.position = Vector3.Lerp(transform.position, wantedPosition, 7.5f * Time.deltaTime);
        //putting camera position a bit upper
        transform.LookAt(lookFor.position + Vector3.up);


    }

    public void LookLeft()
    {
        CameraAngle(true);
    }

    public void LookRight()
    {
        CameraAngle(false);
    }
    
    //if swipe left 90 if not -90, rotate vector
    public void CameraAngle(bool left)
    {
        if (left)
            ySet = Quaternion.Euler(0, 90, 0) * ySet;
        else
            ySet = Quaternion.Euler(0, -90, 0) * ySet;
    } 
}
