# ctinator


This project was developed as a part of the [commercetools](https://www.commercetools.com) Summer Hack 2018. 


ctinator is a [Stride](https://www.stride.com) bot which can do the following

- Sync categories from on commercetools project to another project.
- Display a product in the form of a dialog box.

This project is merely a spring-boot REST service which exposes several endpoints that the Stride application
can connect to for sending messages back and forth between Stride and the server while also doing some message processing.

### Usage

- After the bot is deployed somewhere to install it on stride:
    - Add a custom app to a conversation on stride.
    - Paste the installation URL of where ctinator is deployed via [https://developer.atlassian.com/apps/](https://developer.atlassian.com/apps/)
    - And now simply talk to it! :)
- Here's what it can do:

|Message|What it does|
|-------------|-------------|
|Hi @ctinator| The bot will respond with a good morning greeting containing stats summary of the set commercetools project with STRIDE_PROJECT_KEY|
|Asking it to show/view/display product with id| ctinator will respond with dialog containing the commercetools product info and a link its location on the MC and the playground.|
|Saying anything that contains sync| Would sync all the categories from the STRIDE_PROJECT_KEY to STRIDE_TARGET_PROJECT_KEY|
#### Query a Product
![screen shot 2018-06-26 at 09 43 13](https://user-images.githubusercontent.com/9512131/41896662-7b3860a4-7925-11e8-923d-576a89ced007.png)
#### View a Product
![screen shot 2018-06-26 at 09 42 37](https://user-images.githubusercontent.com/9512131/41896664-7c4710f8-7925-11e8-9a24-c66647efc0f9.png)
#### Sync commercetools categories
![screen shot 2018-06-26 at 09 37 22](https://user-images.githubusercontent.com/9512131/41896666-7d687efe-7925-11e8-86e1-189805c44dc9.png)






### Development

- The bot is written as an exposed Spring-boot service using the [stride-java-api](https://bitbucket.org/atluiz/stride-api-java/src/master/) 
for communicating with the stride application and the [commercetools-jvm-sdk](https://github.com/commercetools/commercetools-jvm-sdk) for
communicating with commercetools.

- The service uses the [commercetools-sync-java](https://github.com/commercetools/commercetools-sync-java) library for the sync process.

- The following environment variables are needed to be able to start the application 

|Environment Variable|Description|
|-------------|-------------|
|APP_EXTERNAL_URL| The Url of where the service is exposed |
|STRIDE_HELLO_BOT_CLIENT_ID| Can be found after an app is created [here](https://developer.atlassian.com/apps/create)|
|STRIDE_HELLO_BOT_SECRET| Can be found after an app is created [here](https://developer.atlassian.com/apps/create)|
|STRIDE_CLIENT_ID| The commercetools source project client id|
|STRIDE_CLIENT_SECRET|The commercetools source project client secret|
|STRIDE_PROJECT_KEY| The commercetools source project key|
|STRIDE_TARGET_CLIENT_ID| The commercetools target project client id|
|STRIDE_TARGET_CLIENT_SECRET| The commercetools target project client secret|
|STRIDE_TARGET_PROJECT_KEY| The commercetools target project key|
