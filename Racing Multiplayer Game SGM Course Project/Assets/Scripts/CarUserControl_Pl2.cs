using System;
using UnityEngine;
using UnityStandardAssets.CrossPlatformInput;
using System.Collections;

namespace UnityStandardAssets.Vehicles.Car
{
    [RequireComponent(typeof (CarController))]
    public class CarUserControl_Pl2 : MonoBehaviour
    {
        private CarController m_Car; // the car controller we want to use
        Rigidbody rb;

        private void Awake()
        {
            // get the car controller
            m_Car = GetComponent<CarController>();
            rb = this.GetComponent<Rigidbody>();
        }


        private void FixedUpdate()
        {
            float h = CrossPlatformInputManager.GetAxis("Horizontal2");
            float v = CrossPlatformInputManager.GetAxis("Vertical2");

            float handbrake = CrossPlatformInputManager.GetAxis("Jump2");
            m_Car.Move(h, v, v, handbrake);
            AnalogueSpeedConverter2.ShowSpeed(rb.velocity.magnitude, 0, 80);
        }
    }
}
