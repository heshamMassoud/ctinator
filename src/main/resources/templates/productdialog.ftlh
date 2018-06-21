<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>App Configuration</title>

    <!-- Import AtlasKit: https://atlaskit.atlassian.com -->
    <link rel="stylesheet" href="/atlaskit/css-reset/dist/bundle.css"/>
    <link rel="stylesheet" href="/atlaskit/reduced-ui-pack/dist/bundle.css"/>

    <!-- Import JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Import the Stride Javascript API -->
    <script src='https://dev-lib.stride.com/javascript/simple-xdm.js'></script>
</head>
<body>
<form action="/save-configuration" method="post" class="aui">
    <div class="ak-field-group">
        <label for="projectKey">Project Key</label>
        <input class="ak-field-text ak-field__size-medium" placeholder="Project Key" type="text" name="projectKey"
               id="projectKey" value="${(ctProjectCredentials.projectKey)!}"
               required autofocus/>
    </div>
    <div class="ak-field-group">
        <label for="clientId">Client Id</label>
        <input class="ak-field-text ak-field__size-medium" placeholder="Client Id" type="text" name="clientId"
               id="clientId" value="${(ctProjectCredentials.clientId)!}"
               required autofocus/>
    </div>
    <div class="ak-field-group">
        <label for="clientSecret">Client Secret</label>
        <input class="ak-field-text ak-field__size-medium" placeholder="Client Secret" type="text" name="clientSecret"
               id="clientSecret"
               value="${(ctProjectCredentials.clientSecret)!}" required autofocus/>
    </div>
    <fieldset class="ak-field-group">
        <legend><span>Notification options</span></legend>
        <div class="ak-field-radio">
            <input type="radio" name="option" id="option1" value="option1">
            <label for="option1">Send a notification every time a new object is created</label>
        </div>
        <div class="ak-field-radio">
            <input type="radio" name="option" id="option2" value="option2">
            <label for="option2">Send a daily digest</label>
        </div>
        <div class="ak-field-radio">
            <input type="radio" name="option" id="option3" value="option3">
            <label for="option3">No notifications, kthxbye</label>
        </div>
    </fieldset>
</form>
</body>
</html>

