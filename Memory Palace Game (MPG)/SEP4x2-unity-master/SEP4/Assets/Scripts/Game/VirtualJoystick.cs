using UnityEngine.UI;
using UnityEngine.EventSystems;
using UnityEngine;

public class VirtualJoystick : MonoBehaviour, IDragHandler, IPointerUpHandler, IPointerDownHandler
{
    private Image bgImg;
    private Image joystickImg;

    public Vector3 InputDirection { set; get; }

    private void Start()
    {
        bgImg = GetComponent<Image>();
        joystickImg = transform.GetChild(0).GetComponent<Image>();
        InputDirection = Vector3.zero;
    }

    public virtual void OnDrag(PointerEventData ped)
    {
        //if we click we do smh
        Vector2 pos = Vector2.zero;
        if (RectTransformUtility.ScreenPointToLocalPointInRectangle
            //where can we go
            (bgImg.rectTransform,
            //where did we click
            ped.position,
            ped.pressEventCamera,
            //return vecto2 point
            out pos))
        {
            //getting where we clicked
            pos.x = (pos.x / bgImg.rectTransform.sizeDelta.x);
            pos.y = (pos.y / bgImg.rectTransform.sizeDelta.y);

            //if its on the right = posx*2+1, if not posx*2.1
            float x = (bgImg.rectTransform.pivot.x == 1) ? pos.x * 2 + 1 : pos.x * 2 - 1;
            float y = (bgImg.rectTransform.pivot.y == 1) ? pos.y * 2 + 1 : pos.y * 2 - 1;

            //transforming 2d into 3d vector, stopping joystick from gong out of boundaries, if its bigged than 1 get to 1
            InputDirection = new Vector3(-x, 0, y);
            InputDirection = (InputDirection.magnitude > 1) ? InputDirection.normalized : InputDirection;

            //moves image
            joystickImg.rectTransform.anchoredPosition = new Vector3(InputDirection.x * (bgImg.rectTransform.sizeDelta.x / -3),
                InputDirection.z * (bgImg.rectTransform.sizeDelta.y / 3)); 
        }
    }
    
    //moves joystick when click somewhere
    public virtual void OnPointerDown(PointerEventData ped)
    {
        OnDrag(ped);
    }

    //resets joystick on middle after letting it go
    public virtual void OnPointerUp(PointerEventData ped)
    {
        InputDirection = Vector3.zero;
        joystickImg.rectTransform.anchoredPosition = Vector3.zero;
    }
}
