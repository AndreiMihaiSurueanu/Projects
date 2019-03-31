using System;
using UnityEngine;
using UnityStandardAssets.CrossPlatformInput;

namespace UnityStandardAssets.Vehicles.Car
{
    [RequireComponent(typeof(CarController))]
    public class CarUserControl_Pl1 : MonoBehaviour
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
            // pass the input to the car!
            float h = CrossPlatformInputManager.GetAxis("Horizontal1");
            float v = CrossPlatformInputManager.GetAxis("Vertical1");

            float handbrake = CrossPlatformInputManager.GetAxis("Jump1");
            m_Car.Move(h, v, v, handbrake);
            AnalogueSpeedConverter1.ShowSpeed(rb.velocity.magnitude, 0, 80);
        }
    }
}
