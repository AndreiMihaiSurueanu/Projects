using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Countdown : MonoBehaviour {

    public GameObject countDown;
    public AudioSource getReady;
    public AudioSource goAudio;
    [SerializeField] GameObject lapTimer;
    public GameObject carControls;

	void Start () {
        StartCoroutine(CountStart());
	}

    IEnumerator CountStart()
    {
        yield return new WaitForSeconds(1f);
        countDown.GetComponent<Text>().text = "3";
        getReady.Play();
        countDown.SetActive(true);
        yield return new WaitForSeconds(1);
        countDown.SetActive(false);
        countDown.GetComponent<Text>().text = "2";
        getReady.Play();
        countDown.SetActive(true);
        yield return new WaitForSeconds(1);
        countDown.SetActive(false);
        countDown.GetComponent<Text>().text = "1";
        getReady.Play();
        countDown.SetActive(true);
        yield return new WaitForSeconds(1);
        countDown.SetActive(false);
        goAudio.Play();
        lapTimer.SetActive(true);
        carControls.SetActive(true);
    }
}
