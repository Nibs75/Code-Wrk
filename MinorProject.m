%Minor Project Numair Hadi
%a=arduino('COM4','uno');
%Instantiate reactive values
reallyDry=2.8;
moistureThreshold=2.69;
saturatedValue=2.3;
V=readVoltage(a,'A1');


writeDigitalPin(a,'D4',0);
%Create conditional statements on watering the plant
if (V< reallyDry && V>moistureThreshold )
   disp('Signal Recieved, Soil is really dry.Time to water the plant')
    writeDigitalPin(a,'D2',1); 
    pause(1.5);
    writeDigitalPin(a,'D2',0);
    display(V)  
elseif(V<moistureThreshold && V<reallyDry)
     disp('Signal Recieved,Soil has reached moisture limit. Time to water the plant')
    writeDigitalPin(a,'D2',1); 
    pause(1.5);
    writeDigitalPin(a,'D2',0);
    display(readVoltage(a,'A1'))
display(V)  
elseif(V<=saturatedValue)
     disp('Signal Recieved, Soil is wet enough time to stop watering the plant')
    writeDigitalPin(a,'D2',0);
    display(V)  
else
    disp('Error')
end

tic %start timer

max_samples = 1500; % how many times do we check the Moisture sensor?
filter_size = 5; %how many samples do we use in the moving sample
max_value = 2;

tic %start timer which acts as a live feed of moisture of soil
for i = 1:max_samples
 moist_data(i) = readVoltage(a,'A1');
 time_data(i) = toc;
end

plot(time_data,moist_data);
title('Moisture Level Over time ');
xlabel('Time');
ylabel('Voltage [volt]');

for (i = 1:(max_samples-filter_size))
 avg_moist_data(i) = mean(moist_data(i:i+filter_size)); %making data more new and more refined with time
end

plot(time_data(1:max_samples-filter_size),avg_moist_data);
title('Filtered Moisture Level');
xlabel('Time');
ylabel('Voltage [volt]');

for i = 1: (max_samples-filter_size) %threshold data to filter out the voltageq below 2 volts
 if (avg_moist_data(i) > max_value)
 avg_max_data(i) = 1;
 else
 avg_max_data(i) = 0;
 end
end

plot(time_data(1:max_samples-filter_size),avg_max_data)   %plotting final graph
title('Filtered and Maximum Moisture')
xlabel('Time')
ylabel('Voltage [volt]')