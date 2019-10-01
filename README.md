# The Brief:

Create a mini version of the Moneybox app that will allow existing users to login, check their account and add money to their moneybox.

## Part A

### Bug 1 - Layout does not look as expected

The layout for the login screen was not as expected due to incorrect constraints for the UI components.
The Moneybox logo needed to be constrained horizontally to center it.
The Email TIL needed to be constrained to the logo.
The text input components needed to have constrains to the components above in order to stack them in the right order and maintain their positions on the layout towards the top of the screen.
The margins of the TILs were adjusted to have 8dp from the start and end of the component.
The sign in button needed to be constrained to both sides, the bottom and the bottom of the last TIL so as to center it in the middle of the rest of the layout.
The bottom of the animation needed to be constrained to the top of the sign in button.

### Bug 2 - Validation is incorrect

The til_name component does the name regex check on the email edit text and not the name edit text.
The til_email error is updated when name check fails. 
The til_name will fail if there is nothing entered. So a check is done to see if the et_name is not empty. If its not empty then the regex check is done.
Set the til errors to null after they have been validated to clear the error messages. Set isErrorEnabled to false to clear the space that was added by the error. (This is to help users see which entry is still invalid if there are multiple errors.)
Changed the name regex to allow for spaces.

### Bug 3 - Animation is looping incorrectly

Set the frame max and min for the first loop leave the repeat values as default which will run once.
Add an animator update listener to see if the value of animation fraction has reached 1 which indicates the animation has played through.
Once the first playthrough is complete set the infinite repeating values and the min and max frames.

## Part B - Add 2 new screens

Libraries Used:
Retrofit2
RxJava2
RxAndroid2
Gson

Set the text view to hide if there is no string in the intent's bundle

First create the Retrofit object which will use the Gson converter to convert Json responses to serialized POJOs.
Use RxJava adapter to allow for threading of the network connections using Observable interface.
The responses then can be dealt with using the overridden methods of the Observable where the json is converted into java objects.
After the user logs there is a request for the authorization token.
Once this token is received the products are downloaded and incorporated into the array adapter. Each item has an on click listener which parcels the product object into an intent extra and opens the individual account screen.

The product object is retreived from the intent bundle and used to fill in the textviews for the product name, plan value and moneybox.
An onclick listener is added to the button which will initiate a call to add 10 pounds to a certain account.
Some of the products return a 500 server error which mentioning that no more money can be added and so this is dealt with in the onError overriden method of the observable. 
