package com.codifyd.automation.util;


public class MyExceptions {
	class InvalidFileException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3251223926437009287L;

		public InvalidFileException(String s) {
			super(s);
		}
	}

	class InvalidDirectoryException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8651606537680981918L;

		public InvalidDirectoryException(String s) {
			super(s);
		}
	}

	class NullValueException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4821622202752346357L;

		public NullValueException(String s) {
			super(s);
		}
	}
}
