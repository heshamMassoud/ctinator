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
        <h1>${(productName)!}</h1>
    </div>
    <br>
    <a class="ak-button__appearance-link" href="${(playgroundUrl)!}">Playground</a> |
    <a class="ak-button__appearance-link" href="${(mcUrl)!}">Merchant Center</a> |
    <button class="ak-button__appearance-default">Unpublish</button>

    <div class="ak-field-group" >
        <img width="20%" src="https://img.rewe-static.de/1910439/25946143_digital-image.png?resize=1320px:1320px&output-quality=90&output-format=image/webp&background-color=FFFFFF"
             />
    </div>

    <div class="ak-field-group">
        <label for="desc">Description</label>
        <textarea class="ak-field-textarea" name="desc" id="desc" required autofocus>${(desc)!}</textarea>
    </div>

    <div class="ak-field-group">
        <label for="vars">Variants</label>
        <table>
            <tr>
                <td><a class="ak-button__appearance-link" href="${(playgroundUrl)!}">Master Variant: Size 160g</a></td>
            </tr>
            <tr>
                <td><a class="ak-button__appearance-link" href="${(playgroundUrl)!}">Variant 1: Size 200g</a></td>
            </tr>
            <tr>
                <td><a class="ak-button__appearance-link" href="${(playgroundUrl)!}">Variant 2: Size 200g</a></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>

