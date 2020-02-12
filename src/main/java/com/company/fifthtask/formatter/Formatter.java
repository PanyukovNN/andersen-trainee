package com.company.fifthtask.formatter;

import com.company.fifthtask.SymbolsAmount;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Formatter {

	public String format(final SymbolsAmount symbolsAmount){
		List<String> output = new ArrayList<>();
		for (Map.Entry<Character, Integer> entry : symbolsAmount.getSymbolsAmount().entrySet()) {
			output.add(String.format("\"%s\" - %d", entry.getKey(), entry.getValue()));
		}
		return StringUtils.join(output, "\n");
	}
}
