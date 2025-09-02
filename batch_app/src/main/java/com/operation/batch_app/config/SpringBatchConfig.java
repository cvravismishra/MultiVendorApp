package com.operation.batch_app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.operation.batch_app.entity.Customer;
import com.operation.batch_app.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

	@Autowired
	private CustomerRepository repository;

	@Bean
	public FlatFileItemReader<Customer> read()
	{
		FlatFileItemReader<Customer> fir = new FlatFileItemReader<Customer>();
		fir.setResource(new FileSystemResource("src/main/resources/customer.csv"));
		fir.setName("Customer_Record");
		fir.setLinesToSkip(1);
		fir.setLineMapper(lineMapper());
		return fir;
		
		
	}
	
	
	public LineMapper<Customer> lineMapper()
	{
		DefaultLineMapper<Customer> dlm = new DefaultLineMapper<Customer>();
		
		DelimitedLineTokenizer dt = new DelimitedLineTokenizer();
		dt.setDelimiter(",");
		dt.setStrict(false);
		dt.setNames("id","name","email","salary","profession");
		
		BeanWrapperFieldSetMapper<Customer> bfs = new BeanWrapperFieldSetMapper<Customer>();
		bfs.setTargetType(Customer.class);
		
		dlm.setLineTokenizer(dt);
		dlm.setFieldSetMapper(bfs);
		
		return dlm;
		
	}
	
	
	@Bean
	public CustomerProcessor processor()
	{
		return new CustomerProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Customer> write()
	{
		RepositoryItemWriter<Customer> rit = new RepositoryItemWriter<Customer>();
		rit.setRepository(repository);
		rit.setMethodName("save");
		return rit;
	}
	
	
	@Bean
	public Job job(Step step,JobRepository jobRepository) {
	    return new JobBuilder("myJob",jobRepository)
	            .incrementer(new RunIdIncrementer())
	            .flow(step)
	            .end()
	            .build();
	}
	
	@Bean
	public TaskExecutor executor()
	{
		SimpleAsyncTaskExecutor exeAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
		exeAsyncTaskExecutor.setConcurrencyLimit(2);
		return exeAsyncTaskExecutor;
	}
	
	@Bean
	public Step step(ItemReader<Customer> reader, ItemProcessor<Customer, Customer> processor,
	                 ItemWriter<Customer> writer,JobRepository jobRepository,PlatformTransactionManager transactionManager) {
	    return new StepBuilder("myStep",jobRepository)
	            .<Customer, Customer>chunk(10,transactionManager)
	            .reader(reader)
	            .processor(processor)
	            .writer(writer)
	            .build();
	}

}
