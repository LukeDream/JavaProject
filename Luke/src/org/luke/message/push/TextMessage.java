package org.luke.message.push;

public class TextMessage extends Message {

		private Text text ;

		public Text getText() {
			return text;
		}

		public void setText(Text text) {
			this.text = text;
		}
}
