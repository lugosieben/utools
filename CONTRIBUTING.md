# Contributing

## What features to add
UTools is **not** a cheat or hacked client.
If you're unsure whether a feature is considered cheating, open an [issue](https://github.com/lugosieben/utools/issues/new).

If the feature introduces elements that are partially legitimate and partially questionable in terms of fair play, it may be acceptable to add it. However, this should only be done if the advantages of the legitimate aspects outweigh any potential concerns about cheating (for example Fog)

## Code Styling

- Spaces
  - There should always be a space between statement and brackets. (for example: `if (a == b) {}`, not `if(a == b){}`)
  - There should always be spacing around operators. (for example `a < b` instead of `a<b`)
- Imports
  - Wildcards (`*`) in import statements  should **only** be used when every import is needed. (basically never)
  - `import static`s should always be below the normal `import`s, seperated by a new line.
- Variables
  - Use **descriptive** variable names. (for example `screenshotPath` instead of `path`)
  - Loop variables should be descriptive, but an `i` variable for small loops is allowed.
- Mixins
  - Function names should be the same as the injected method. In cases with non-descriptive function names (For example an injection for `method_1661`), use a descriptive function name (in this example, `onScreenshot`)
  - Mixin classes should be named after the thing they do, not the class that they inject. (for example: `ClientTimeMixin` instead of `ClientWorldPropertiesMixin`)
- Commands
  - Commands should be registered in a `register` method which should be called in the `registerCommands()` function of `Commands`
  - Execute the commands code in another method, not `register`

> **In general, just try to make the code as reader-friendly as possible.**
  
## Texts
  - Every text displayed to the user should be translatable. (Console texts should **not** be translatable)
  - When creating a feature, you must create the english translation for the texts.
  - Notifications that an action had success / failed are displayed with the `HudMessage` util.
  - Anything else is displayed as a chat message.

## Pull Requests

Your Pull Request should include a brief summary / explanation of the added feature and should include (if applicable) a link to the issue which it resolves.
