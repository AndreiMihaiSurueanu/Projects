using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour {

    private const float TIME_BEFORE_START = 3.0f;

    public float moveSpeed = 5.0f;
    public float drag = 0.5f;
    public float jumpforce = 7;
    public float terminalRotationSpeed = 25.0f;
    public VirtualJoystick moveJoystick;

    
    private Rigidbody controller;
    private Transform camTransform;
    

    private float startTime = 0;

    private void Start()
    {
        controller = GetComponent<Rigidbody>();
        controller.maxAngularVelocity = terminalRotationSpeed;
        controller.drag = drag;
        startTime = Time.time;
        
        //gets main camera tag
        camTransform = Camera.main.transform;
    }

    private void Update()
    {
        if (Time.time - startTime < TIME_BEFORE_START)
            return;

        //checks for the clicks
        Vector3 dir = Vector3.zero;
        dir.x = Input.GetAxis("Horizontal");
        dir.z = Input.GetAxis("Vertical");


        if (moveJoystick.InputDirection != Vector3.zero)
        {
            dir = moveJoystick.InputDirection;
        }

        //rotate direction vector with camera
        Vector3 rotatedDir = camTransform.TransformDirection(dir);
        //killing y component to avoid bugs
        rotatedDir = new Vector3(rotatedDir.x, 0, rotatedDir.z);
        //killing y can kill some movement, normalize deals with it
        rotatedDir = rotatedDir.normalized * dir.magnitude;

        //adding force
        controller.AddForce(rotatedDir * moveSpeed);
       
    }

    public void Jump()
    {
        if (Time.time - startTime < TIME_BEFORE_START)
            return;
        controller.AddForce(Vector3.up * jumpforce, ForceMode.Impulse);

    }

    public void Teleport()
    {
        transform.position = new Vector3(-377.694f, 3.05f, -48.656f);
    }
}
