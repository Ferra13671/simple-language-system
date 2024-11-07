# SimpleLanguageSystem
A simple system for loading and getting texts translated into different languages.

# How to use?
## 1.Creating a file with translated texts
First of all you need to create a file where all translated texts will be stored (1 file should contain texts for 1 language only!). The format of the file is not important at all.
<p>
The structure of text storage should be as follows:
<p>
“key": text
<p>
Example:
<p>
"hello": Hello World!
<p>
Also files can have comments, for this purpose you should add // before the comment.
<p>


## 2.Loading from a file to the system
Loading translated texts from a file is done via the <b>loadTranslations(inputStream, lang)</b> method. 
<p>
<b>inputStream</b> — InputStream of the file from which the translated texts will be loaded.
<p>
<b>lang</b> — The name of the language to which the translated texts will be assigned.
<p>

## 3.Getting the desired translated text
To get the text from the system of languages you can use methods <b>translate(key)</b> and <b>translate(key, lang)</b>.
<p>
<b>key</b> - the key to which the desired translated text was bound.
<p>
<b>lang</b> - the language for which the translated text is to be retrieved.
<p>
The <b>translate(key)</b> method will use the current language as the desired language, which will be obtained using <b>currentLanguageGetter</b>, which can be set using the <b>setCurrentLanguageGetter(getter)</b> method.

