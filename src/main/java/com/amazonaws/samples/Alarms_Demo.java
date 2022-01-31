package com.amazonaws.samples;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.DescribeAlarmsRequest;
import software.amazon.awssdk.services.cloudwatch.model.DescribeAlarmsResponse;
import software.amazon.awssdk.services.cloudwatch.model.MetricAlarm;

public class Alarms_Demo 
{
	public static void main(String[] args) 
	{
		CloudWatchClient cw = CloudWatchClient.builder().build();

		boolean done = false;
		String new_token = null;

		while(!done)
		{
			DescribeAlarmsResponse response;
			if (new_token == null) 
			{
				DescribeAlarmsRequest request = DescribeAlarmsRequest.builder().build();
		        response = cw.describeAlarms(request);
			}
			else 
			{
				DescribeAlarmsRequest request = DescribeAlarmsRequest.builder()
						.nextToken(new_token)
						.build();
		        response = cw.describeAlarms(request);
			}


		    for(MetricAlarm alarm : response.metricAlarms()) 
		    {
		        System.out.printf("Retrieved alarm %s", alarm.alarmName()+"\n");
		    }

		    if(response.nextToken() == null) 
		    {
		        done = true;
		    }
		    else 
		    {
		        new_token = response.nextToken();
		    }
		}
	}

}
