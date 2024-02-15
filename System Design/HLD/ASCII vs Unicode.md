
Ref link: https://stackoverflow.com/questions/19212306/whats-the-difference-between-ascii-and-unicode

**ASCII, Origins**

As stated in the other answers, ASCII uses 7 bits to represent a character. By using 7 bits, we can have a maximum of 2^7 (= 128) distinct combinations*. Which means that we can represent 128 characters maximum.

> **Wait, 7 bits? But why not 1 byte (8 bits)?**
> 
> The last bit (8th) is used for avoiding errors as [parity bit](https://en.wikipedia.org/wiki/Parity_bit). This was relevant years ago.

Most ASCII characters are printable characters of the alphabet such as abc, ABC, 123, ?&!, etc. The others are [control characters](https://en.wikipedia.org/wiki/Control_character) such as [carriage return, line feed](https://stackoverflow.com/a/12747850/1132522), tab, etc.

See below the binary representation of a few characters in ASCII:

```
0100101 -> % (Percent Sign - 37)
1000001 -> A (Capital letter A - 65)
1000010 -> B (Capital letter B - 66)
1000011 -> C (Capital letter C - 67)
0001101 -> Carriage Return (13)
```

See the full ASCII table [over here](http://www.theasciicode.com.ar/).

ASCII was meant for English only.

> **What? Why English only? So many languages out there!**
> 
> Because the center of the computer industry was in the USA at that time. As a consequence, they didn't need to support accents or other marks such as á, ü, ç, ñ, etc. (aka [diacritics](https://en.wikipedia.org/wiki/Diacritic)).

**ASCII Extended**

Some clever people started using the 8th bit (the bit used for parity) to encode more characters to support their language (to support "é", in French, for example). Just using one extra bit doubled the size of the original ASCII table to map up to 256 characters (2^8 = 256 characters). And not 2^7 as before (128).

```
10000010 -> é (e with acute accent - 130)
10100000 -> á (a with acute accent - 160)
```

The name for this "ASCII extended to 8 bits and not 7 bits as before" could be just referred as "extended ASCII" or "8-bit ASCII".

As [@Tom](https://stackoverflow.com/users/2226988/tom-blodget) pointed out in his comment below there is no such thing as "[extended ASCII](https://en.wikipedia.org/wiki/Extended_ASCII)" yet this is an easy way to refer to this 8th-bit trick. There are many variations of the 8-bit ASCII table, for example, the [ISO 8859-1, also called ISO Latin-1](https://en.wikipedia.org/wiki/Extended_ASCII#ISO_8859_and_proprietary_adaptations).

**Unicode, The Rise**

ASCII Extended solves the problem for languages that are based on the Latin alphabet... what about the others needing a completely different alphabet? Greek? Russian? Chinese and the likes?

We would have needed an entirely new character set... that's the rational behind Unicode. Unicode doesn't contain every character from every language, but it sure contains a gigantic amount of characters ([see this table](https://unicode-table.com/en/#cjk-unified-ideographs-extension-a)).

You cannot save text to your hard drive as "Unicode". Unicode is an abstract representation of the text. You need to "encode" this abstract representation. That's where an [encoding](https://en.wikipedia.org/wiki/Character_encoding) comes into play.

**Encodings: UTF-8 vs UTF-16 vs UTF-32**

[This answer](https://stackoverflow.com/a/22404874/1132522) does a pretty good job at explaining the basics:

- UTF-8 and UTF-16 are variable length encodings.
- In UTF-8, a character may occupy a minimum of 8 bits.
- In UTF-16, a character length starts with 16 bits.
- UTF-32 is a fixed length encoding of 32 bits.

UTF-8 uses the ASCII set for the first 128 characters. That's handy because it means ASCII text is also valid in UTF-8.

Mnemonics:

- UTF-**8**: minimum **8** bits.
- UTF-**16**: minimum **16** bits.
- UTF-**32**: minimum and maximum **32** bits.