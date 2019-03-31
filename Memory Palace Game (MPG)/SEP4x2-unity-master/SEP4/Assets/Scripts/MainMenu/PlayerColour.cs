using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerColour : MonoBehaviour {

    public GameObject player;

    public void PlayerColorChange(Material mat)
    {
        player.GetComponent<Renderer>().material = mat;

        if (mat.name == "mat_6")
        {
            PlayerPrefs.SetInt("ballColor", 6);
        }

        else if (mat.name == "Ground_Texture_Material")
        {
            PlayerPrefs.SetInt("ballColor", 1);
        }

       else if (mat.name == "House_color")
        {
            PlayerPrefs.SetInt("ballColor", 2);
        }

        else if (mat.name == "mat_2")
        {
            PlayerPrefs.SetInt("ballColor", 3);
        }

        else if (mat.name == "mat_3")
        {
            PlayerPrefs.SetInt("ballColor", 4);
        }

        else if (mat.name == "mat_4")
        {
            PlayerPrefs.SetInt("ballColor", 5);
        }

        else if (mat.name == "mat_5")
        {
            PlayerPrefs.SetInt("ballColor", 7);
        }

        else if (mat.name == "mat_7")
        {
            PlayerPrefs.SetInt("ballColor", 8);
        }

    }
}
