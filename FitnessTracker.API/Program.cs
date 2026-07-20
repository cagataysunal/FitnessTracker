using Scalar.AspNetCore;

var builder = WebApplication.CreateBuilder(args);
builder.WebHost.UseUrls("http://localhost:5005");
builder.Services.AddOpenApi();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
    app.MapScalarApiReference();
}

const string hardcodedUserName = "admin";
const string hardcodedPassword = "admin";

app.MapGet("/", () => "Hello World!");

app.MapPost("/login", (LoginRequest loginRequest) =>
{
    if (loginRequest.name == hardcodedUserName && loginRequest.password == hardcodedPassword)
    {
        return Results.Ok(new { Message = "Login successful" });
    }
    else
    {
        return Results.Unauthorized();
    }
});

app.Run();