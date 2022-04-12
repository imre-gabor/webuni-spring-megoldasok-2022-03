package hu.webuni.hr.minta.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.config.HrConfigProperties;
import hu.webuni.hr.minta.config.HrConfigProperties.Smart;
import hu.webuni.hr.minta.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		
		//Duration.between(employee.getDateOfStartWork(), LocalDateTime.now()).toDays();

		double yearsWorked = ChronoUnit.DAYS.between(employee.getDateOfStartWork(), LocalDateTime.now()) / 365.0;
		
		Smart smartConfig = config.getSalary().getSmart();
		
		//1. megoldás: fixen 3 limit és percent
//		if(yearsWorked > smartConfig.getLimit3())
//			return smartConfig.getPercent3();
//		
//		if(yearsWorked > smartConfig.getLimit2())
//			return smartConfig.getPercent2();
//		
//		if(yearsWorked > smartConfig.getLimit1())
//			return smartConfig.getPercent1();
//		return 0;
		
		//2. megoldás: dinamikusan, akárhány limit és percent
		TreeMap<Double, Integer> limits = smartConfig.getLimits();
		
//		Integer maxPercent = null;
//		
//		for (Entry<Double, Integer> entry: limits.entrySet()) {
//			if(yearsWorked > entry.getKey())
//				maxPercent = entry.getValue();
//			else
//				break;
//		}
//
//		return maxPercent == null ? 0 : maxPercent;
		
		//3. megoldás
//		Optional<Double> optionalMax = limits.keySet().stream()
//			.filter(k -> yearsWorked >= k)
//			.max(Double::compare);
//		
//		return optionalMax.isEmpty() ? 0 : limits.get(optionalMax.get());
		
		//4. megoldás
		Entry<Double, Integer> floorEntry = limits.floorEntry(yearsWorked);
		return floorEntry == null ? 0 : floorEntry.getValue();
	}

}
